package ua.nix.finance.persistence.entity;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import ua.nix.finance.annotations.NotValidDigit;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "transaction_generator", sequenceName = "finance_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "funds")
    @NotNull
    @NotValidDigit(value = "0",message = "Amount should be not 0")
    private Double amount;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP")
    private Instant dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    List<Category> categoryList = new ArrayList<>();

    public Transaction() {

    }

    public Transaction(Account account, Double amount, Instant dateTime, List<Category> categoryList){
        this.account = account;
        this.amount = amount;
        this.dateTime = dateTime;
        for(Category category : categoryList){
            this.categoryList.add(category);
            category.setTransactionList(this);
        }
    }

    public Long getId() {
        return id;
    }

}
