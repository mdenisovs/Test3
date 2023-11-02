package org.task3.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.task3.dtos.SubscriptionRequestDto;
import org.task3.dtos.SubscriptionResponseDto;
import org.task3.services.SubscriptionService;

import java.util.List;

@Tag(name = "Service", description = "Service controller for subscription support")
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    private final SubscriptionService subscriptionService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSubscription(@Valid @RequestBody
                                                       @Parameter(name = "Subscription request") SubscriptionRequestDto request) {
        return subscriptionService.save(request);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSubscription(@Valid @RequestBody
                                                       @Parameter(name = "Subscription request")SubscriptionRequestDto request) {
        return subscriptionService.update(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@Parameter(name = "Subscription id")
                                                                       @PathVariable(name = "id") Long id) {
        return subscriptionService.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionResponseDto>> getAll() {
        return subscriptionService.getAll();
    }
}