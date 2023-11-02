package org.task3.controllers;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subscription created"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 409, message = "Subscription exists"),
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSubscription(@Valid @RequestBody
                                                       @Parameter(name = "Subscription request") SubscriptionRequestDto request) {
        return subscriptionService.save(request);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription modified"),
            @ApiResponse(code = 404, message = "Subscription not found")
    })
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSubscription(@Valid @RequestBody
                                                       @Parameter(name = "Subscription request") SubscriptionRequestDto request) {
        return subscriptionService.update(request);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription found"),
            @ApiResponse(code = 404, message = "Subscription not found")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable(name = "id") Long id) {
        return subscriptionService.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SubscriptionResponseDto>> getAll() {
        return subscriptionService.getAll();
    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription deleted")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        return subscriptionService.delete(id);
    }
}