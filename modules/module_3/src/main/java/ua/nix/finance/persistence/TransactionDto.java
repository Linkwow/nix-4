package ua.nix.finance.persistence;

import java.time.Instant;
import java.util.List;

public class TransactionDto {

    private final Double amount;
    private final Instant dateTime;
    private final List<String> categories;

    public TransactionDto(Double amount, String dateTime, List<String> categories) {
        this.amount = amount;
        var sv = new StringBuilder(dateTime);
        sv.append("Z");
        this.dateTime = Instant.parse(sv);
        this.categories = categories;
    }

    public Double getAmount() {
        return amount;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public List<String> getCategories() {
        return categories;
    }
}
