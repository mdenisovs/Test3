package org.task3.services;

import org.springframework.http.ResponseEntity;
import org.task3.dtos.SubscriptionRequestDto;
import org.task3.dtos.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    ResponseEntity<Void> save(SubscriptionRequestDto request);
    ResponseEntity<Void> update(SubscriptionRequestDto request);
    ResponseEntity<SubscriptionResponseDto> get(Long id);
    ResponseEntity<List<SubscriptionResponseDto>> getAll();
    ResponseEntity<Void> delete(Long id);
}
