package ua.nix.finance.repository;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.finance.exceptions.CategoryExtractException;
import ua.nix.finance.persistence.entity.Category;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository {

    private static CategoryRepository instance;
    private Session session;
    private Logger logger;

    private CategoryRepository(Session session){
        this.session = session;
        this.logger = LoggerFactory.getLogger(CategoryRepository.class);
    }

    public List<Category> getCategoryForTransaction(List<String> categoryList) throws CategoryExtractException {
        try {
            logger.info("Starting to get Categories from data base.");
            TypedQuery<Category> query = session.createQuery("select c from Category c where c.name in (:categoriesName)", Category.class).
                    setParameter("categoriesName", categoryList);
            List<Category> categories = query.getResultList();
            logger.info("Categories was taken from database successful.");
            return categories;
        } catch (RuntimeException runtimeException){
            logger.error("Categories was taken from database unsuccessful.");
            throw new CategoryExtractException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static CategoryRepository getInstance(Session session) {
        if(instance == null){
            instance = new CategoryRepository(session);
        }
        return instance;
    }
}
