package ua.nix.finance.persistence.entity;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Email;
import ua.nix.finance.persistence.AbstractEntity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(String email, String phoneNumber, String initials){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.initials = initials;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInitials() {
        return initials;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setAccountList(Account accountNumber) {
        accountList.add(accountNumber);
    }
}
