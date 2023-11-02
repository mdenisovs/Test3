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
@Table(name = "subscriptions")
@Validated
public class Subscription {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate startDate;

    public void setSubmissionStartDate(String birthday) {
        this.startDate = LocalDate.parse(birthday, Formatter.formatter);
    }
}