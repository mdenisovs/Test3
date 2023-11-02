package org.task3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.task3.dtos.SubscriptionResponseDto;
import org.task3.dtos.converters.SubscriptionConverter;
import org.task3.infrastructure.entities.Subscription;
import org.task3.dtos.SubscriptionRequestDto;
import org.task3.infrastructure.repositories.SubscriptionRepository;
import org.task3.infrastructure.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Void> save(SubscriptionRequestDto request) {
        var entity = repository.findById(request.getId());
        if(entity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        var user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var subscription = new Subscription();
        subscription.setId(request.getId());
        subscription.setUser(user.get());
        subscription.setStartDate(LocalDate.now().plusDays(1));
        repository.save(subscription);
        log.info("{} saved for {}", subscription, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> update(SubscriptionRequestDto request) {
        var entity = repository.findById(request.getId());
        if(entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var subscription = entity.get();
        subscription.setUser(user.get());
        repository.save(subscription);
        log.info("{} updated for {}", subscription, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> get(Long id) {
        var entity = repository.findById(id);
        if(entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var response = SubscriptionConverter.convert(entity.get(), modelMapper);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubscriptionResponseDto>> getAll() {
        return new ResponseEntity<>(
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(repository.findAll().iterator(),
                                Spliterator.ORDERED), false)
                .map(it -> SubscriptionConverter.convert(it, modelMapper))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
