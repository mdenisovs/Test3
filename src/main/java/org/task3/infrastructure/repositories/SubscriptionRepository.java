package org.task3.infrastructure.repositories;

import org.springframework.data.repository.CrudRepository;
import org.task3.infrastructure.entities.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}