package ua.nix.finance.service;

import ua.nix.finance.exceptions.TransactionCreatedException;
import ua.nix.finance.persistence.TransactionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DtoService {

    private static DtoService instance;
    private Double amount;
    private String dateTime;
    private List<String> categories = new ArrayList<>();

    private DtoService() {

    }

    public TransactionDto createTransactionDto(String[] parameters) throws TransactionCreatedException {
        try {
            amount = Double.parseDouble(Optional.ofNullable(parameters[0]).orElseThrow());
            dateTime = Optional.ofNullable(parameters[1]).orElseThrow();
            for (int index = 2; index < parameters.length; index++) {
                categories.add(Optional.ofNullable(parameters[index]).orElseThrow());
            }
        } catch (NoSuchElementException | NumberFormatException noSuchElementException) {
            throw new TransactionCreatedException("Transaction parameter cannot be empty", noSuchElementException);
        }
        return new TransactionDto(amount, dateTime, categories);
    }

    public static DtoService getInstance() {
        if (instance == null) {
            instance = new DtoService();
        }
        return instance;
    }
}
