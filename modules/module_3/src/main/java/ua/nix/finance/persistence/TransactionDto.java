package ua.nix.finance.persistence;

import java.time.Instant;
import java.time.ZonedDateTime;;
import java.util.List;

public class TransactionDto {

    private Double amount;
    private Instant dateTime;
    private List<String> categories;
    private ZonedDateTime zonedDateTime = ZonedDateTime.now();

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
