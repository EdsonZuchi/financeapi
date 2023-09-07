package io.github.edsonzuchi.financeapi.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@NotNull
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title is null")
    @NotBlank(message = "title is blank")
    @Column(name = "title",
            length = 45)
    private String title;

    @Column(name = "description",
            columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "user is null")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reference_date",
            columnDefinition = "DATE")
    private LocalDate referenceDate;

    @NotNull(message = "value is null")
    @Column(name = "value")
    private Double value;

    @Column(name = "installments")
    private Integer installments;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
