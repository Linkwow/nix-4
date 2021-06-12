package ua.nix.finance.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.finance.exceptions.AccountAccessException;
import ua.nix.finance.persistence.entity.Account;

public class AccountRepository {
    private static AccountRepository instance;
    private final Session session;
    private final Logger logger;

    private AccountRepository(Session session){
        this.session = session;
        logger = LoggerFactory.getLogger(AccountRepository.class);
    }

    public Account getById(Long id) throws AccountAccessException {
        try {
            logger.info("Starting to get Account entity by id");
            Query<Account> query = session.createQuery("select a from Account a where a.id = ?1", Account.class).setParameter(1, id);
            logger.info("Account was taken successful");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException){
            logger.error("Account was taken unsuccessful");
            throw new AccountAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static AccountRepository getInstance(Session session) {
        if(instance == null){
            instance = new AccountRepository(session);
        }
        return instance;
    }
}
