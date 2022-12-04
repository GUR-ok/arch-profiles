package ru.gur.archprofiles.web.profile.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class ProfileResponse {

    private String email;

    private String fullName;

    private Integer age;

    private String firstName;

    private String lastName;

    private String passportNumber;
}
