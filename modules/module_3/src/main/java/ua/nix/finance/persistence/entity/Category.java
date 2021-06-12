package ua.nix.finance.persistence.entity;

import javax.persistence.*;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Categories")
public class Category {

    public enum CategoryType {
        DEBET("DEBET"), CREDIT("CREDIT");

        private String value;

        CategoryType(String value){
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_generator", sequenceName = "finance_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CategoryType", length = 8)
    @NotNull
    private CategoryType categoryType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryList")
    private List<Transaction> transactionList;

    public Category(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setTransactionList(Transaction transaction) {
        this.transactionList.add(transaction);
    }
}
