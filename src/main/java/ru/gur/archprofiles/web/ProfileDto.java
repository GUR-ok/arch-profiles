package ru.gur.archprofiles.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {

    @NotBlank
    private String fullName;

    @NotNull
    private Integer age;
}
