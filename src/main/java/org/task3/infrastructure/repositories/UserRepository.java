package org.task3.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task3.infrastructure.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}