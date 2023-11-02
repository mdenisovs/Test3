package org.task3.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.task3.infrastructure.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}