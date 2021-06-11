package ua.nix.finance.dao;

import ua.nix.finance.persistence.TransactionDto;
import ua.nix.finance.persistence.entity.Account;
import ua.nix.finance.persistence.entity.Category;
import ua.nix.finance.persistence.entity.Transaction;

import java.util.List;

public class TransactionDao {

    private static TransactionDao instance;

    private TransactionDao(){}

    public Transaction create(Account account, TransactionDto transactionDto, List<Category> categoryList){
        try {
            return new Transaction(account,
                    transactionDto.getAmount(),
                    transactionDto.getDateTime(),
                    categoryList);
        } catch (RuntimeException runtimeException){
            throw new RuntimeException(runtimeException.getMessage());
        }
    }

    public static TransactionDao getInstance() {
        if(instance == null){
            instance = new TransactionDao();
        }
        return instance;
    }
}
