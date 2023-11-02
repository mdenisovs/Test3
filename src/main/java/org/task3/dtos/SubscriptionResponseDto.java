package org.task3.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.task3.dtos.constants.Formatter;

import java.time.LocalDate;

@Data
public class SubscriptionResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("startDate")
    private String startDate;

    public void setSubmissionStartDate(LocalDate date) {
        this.startDate = Formatter.formatter.format(date);
    }
}