package org.task3.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.task3.dtos.constants.Formatter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
@Validated
@ToString
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    private String name;

    private String surname;

    private LocalDate birthday;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "user")
    private Set<Subscription> subscriptions = new HashSet<>();

    public void setSubmissionBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday, Formatter.formatter);
    }
}