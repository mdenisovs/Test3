package org.task3.dtos.converters;

import org.modelmapper.ModelMapper;
import org.task3.infrastructure.entities.Subscription;
import org.task3.dtos.SubscriptionResponseDto;

public final class SubscriptionConverter {

    public static Subscription convert(SubscriptionResponseDto subscriptionResponseDto, ModelMapper modelMapper) {
        var subscription = modelMapper.map(subscriptionResponseDto, Subscription.class);
        subscription.setSubmissionStartDate(subscriptionResponseDto.getStartDate());
        return subscription;
    }

    public static SubscriptionResponseDto convert(Subscription subscription, ModelMapper modelMapper) {
        var subscriptionResponseDto = modelMapper.map(subscription, SubscriptionResponseDto.class);
        subscriptionResponseDto.setSubmissionStartDate(subscription.getStartDate());
        return subscriptionResponseDto;
    }

    private SubscriptionConverter() {
    }
}