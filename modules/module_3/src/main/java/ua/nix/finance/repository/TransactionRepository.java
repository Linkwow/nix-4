package ua.nix.finance.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.finance.dao.TransactionDao;
import ua.nix.finance.exceptions.TransactionCreatedException;
import ua.nix.finance.persistence.TransactionDto;
import ua.nix.finance.persistence.entity.Account;
import ua.nix.finance.persistence.entity.Category;
import ua.nix.finance.persistence.entity.Transaction;

import java.util.List;

public class TransactionRepository {
    private static TransactionRepository instance;
    private Logger logger;

    private TransactionRepository() {
        logger = LoggerFactory.getLogger(TransactionRepository.class);
    }

    public Transaction createTransaction(Account account, TransactionDto transactionDto, List<Category> categoryList) throws TransactionCreatedException {
        try {
            logger.info("Creating Transaction");
            Transaction transaction = TransactionDao.getInstance().create(account, transactionDto, categoryList);
            logger.info("Transaction create was successful");
            return transaction;
        } catch (RuntimeException runtimeException){
            logger.error("Transaction create was unsuccessful");
            throw new TransactionCreatedException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }
}
