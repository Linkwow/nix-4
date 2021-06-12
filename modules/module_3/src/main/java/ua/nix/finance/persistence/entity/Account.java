package ua.nix.finance.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_generator", sequenceName = "finance_sequence", allocationSize = 1)
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

    public Long getId() {
        return id;
    }
}
