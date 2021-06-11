package ua.nix.finance.persistence.entity;

import javax.persistence.*;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.TypeDef;
import ua.nix.finance.persistence.AbstractEntity;

@Entity
@Table(name = "Categories")
public class Category extends AbstractEntity {

    public enum CategoryType {
        DEBET("DEBET"), CREDIT("CREDIT");

        private String value;

        CategoryType(String value){
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Category(String name, CategoryType categoryType){
        this.name = name;
        this.categoryType = categoryType;
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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setTransactionList(Transaction transaction) {
        this.transactionList.add(transaction);
    }
}
