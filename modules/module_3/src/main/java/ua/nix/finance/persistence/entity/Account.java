package ua.nix.finance.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ua.nix.finance.persistence.AbstractEntity;


@Entity
@Table(name = "Accounts")
public class Account extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", unique = true)
    @NotBlank
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Transaction> transactionList = new ArrayList<>();

    public Account() {

    }

    public Account(String accountNumber, User user) {
        this.accountNumber = accountNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
