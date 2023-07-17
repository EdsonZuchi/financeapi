package io.github.edsonzuchi.financeapi.orm;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bead")
public class Bead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",
            length = 45)
    private String title;

    @Column(name = "description",
            columnDefinition = "TEXT")
    private String description;

    @Column(name = "reference_date",
            columnDefinition = "DATE")
    private LocalDate referenceDate;

    @Column(name = "value")
    private Double value;

    @Column(name = "installment")
    private Integer installment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*Get e set*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(LocalDate referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
