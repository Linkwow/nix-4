package ua.nix.finance.persistence.entity;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_generator", sequenceName = "finance_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone_number", unique = true)
    @NotNull
    private String phoneNumber;

    @Column(name = "initials")
    @NotNull
    private String initials;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Account> accountList = new ArrayList<>();

    public User(){

    }

    public Long getId() {
        return id;
    }

}
