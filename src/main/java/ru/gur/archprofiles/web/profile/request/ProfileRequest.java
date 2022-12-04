package ru.gur.archprofiles.web.profile.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest {

    @NotBlank
    private String fullName;

    @NotNull
    private Integer age;

    private String firstName;

    private String lastName;

    private String passportNumber;
}
