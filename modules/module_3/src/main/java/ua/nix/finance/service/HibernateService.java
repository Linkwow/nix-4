package ua.nix.finance.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nix.finance.persistence.TransactionDto;
import ua.nix.finance.persistence.entity.Category;
import ua.nix.finance.persistence.entity.Transaction;
import ua.nix.finance.persistence.entity.Account;
import ua.nix.finance.exceptions.CategoryCorrespondException;
import ua.nix.finance.exceptions.FinanceExceptions;
import ua.nix.finance.exceptions.TransactionCreatedException;
import ua.nix.finance.repository.AccountRepository;
import ua.nix.finance.repository.CategoryRepository;
import ua.nix.finance.repository.TransactionRepository;

import java.util.List;
import java.util.Set;

public class HibernateService {
    private static HibernateService instance;
    private final Session session;
    private final Logger logger;
    private final Validator validator;

    private HibernateService(Session session, ValidatorFactory validatorFactory) {
        this.session = session;
        logger = LoggerFactory.getLogger(HibernateService.class);
        validator = validatorFactory.getValidator();
    }

    public Transaction createTransaction(Long id, String[] entityParameters) throws FinanceExceptions {
        try {
            logger.info("Starting create Transaction");
            TransactionDto transactionDto = DtoService.getInstance().createTransactionDto(entityParameters);
            Account account = AccountRepository.getInstance(session).getById(id);
            List<Category> categoryList = CategoryRepository.getInstance(session).getCategoryForTransaction(transactionDto.getCategories());
            if(!categoryList.isEmpty()) {
                checkCategoryCorrectness(categoryList, transactionDto.getAmount());
            } else {
                throw new TransactionCreatedException("There is no such Category in data base");
            }
            Transaction transaction = TransactionRepository.getInstance().createTransaction(account, transactionDto, categoryList);
            session.persist(transaction);
            Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transaction);
            if(!constraintViolations.isEmpty()){
                System.err.println(constraintViolations.iterator().next().getMessage());
                throw new ConstraintViolationException(constraintViolations);
            }
            logger.info("Transaction was create successful");
            return transaction;
        } catch (FinanceExceptions | RuntimeException exception) {
            logger.error("Transaction was create unsuccessful", exception);
            throw new TransactionCreatedException(exception.getMessage(), exception);
        }
    }

    private boolean checkCategoryCorrectness(List<Category> categoryList, Double amount) throws
            CategoryCorrespondException {
        for (Category category : categoryList) {
            if (amount > 0) {
                if (!category.getCategoryType().equals(Category.CategoryType.DEBET)) {
                    throw new CategoryCorrespondException("Category " + category.getName() + " doesn't correspond to current conditions");
                }
            } else if (amount < 0) {
                if (!category.getCategoryType().equals(Category.CategoryType.CREDIT)) {
                    throw new CategoryCorrespondException("Category " + category.getName() + " doesn't correspond to current conditions");
                }
            }
        }
        return true;
    }

    public static HibernateService getInstance(Session session, ValidatorFactory validatorFactory) {
        if(instance == null){
            instance = new HibernateService(session, validatorFactory);
        }
        return instance;
    }
}
