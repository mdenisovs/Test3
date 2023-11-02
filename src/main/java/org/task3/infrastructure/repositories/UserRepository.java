package org.task3.infrastructure.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.task3.infrastructure.entities.User;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE (:date is null OR u.birthday>=:date) AND " +
            "(:surname is null OR u.surname=:surname) AND (:name is null OR u.name=:name)")
    Slice<User> getFiltered(@Param("name") String name, @Param("surname") String surname,
                            @Param("date") LocalDate date, Pageable pageable);
}