package ru.gur.archprofiles.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {

    private String email;

    private String fullName;

    private Integer age;
}
