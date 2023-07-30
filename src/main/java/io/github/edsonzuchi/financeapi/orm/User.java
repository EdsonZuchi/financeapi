package io.github.edsonzuchi.financeapi.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@NotNull
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",
            length = 30,
            unique = true)
    private String username;

    @NotBlank(message = "name is blank")
    @Column(name = "name",
            length = 60)
    private String name;

    @Email(message = "email invalid")
    @Column(name = "email",
            length = 100,
            unique = true)
    private String email;

    @NotNull(message = "birthday is null")
    @PastOrPresent(message = "The date must be in the past")
    @Column(name = "birthday",
            columnDefinition = "DATE")
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
