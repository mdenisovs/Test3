package org.task3.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.task3.dtos.constants.Formatter;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("subscriptions")
    private List<SubscriptionResponseDto> subscriptions;

    public void setSubmissionBirthday(LocalDate birthday) {
        this.birthday = Formatter.formatter.format(birthday);
    }
}