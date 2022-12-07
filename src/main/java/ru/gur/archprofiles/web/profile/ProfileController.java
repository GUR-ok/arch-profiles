package ru.gur.archprofiles.web.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archprofiles.exception.NotAuthorizedException;
import ru.gur.archprofiles.service.profile.ProfileService;
import ru.gur.archprofiles.web.profile.request.ProfileRequest;
import ru.gur.archprofiles.web.profile.response.ProfileResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.UUID;

import static ru.gur.archprofiles.utils.TokenUtils.getProfileIdFromPayload;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(path = "/profiles")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID create(@RequestBody String email) {
        Assert.hasText(email, "email must not be blank");

        return profileService.create(email);
    }

    @PatchMapping(path = "/profiles/{id}")
    public void update(@PathVariable(name = "id") UUID id,
                       @Valid @RequestBody ProfileRequest profileRequest,
                       @RequestHeader("x-jwt-token") String token) {
        final UUID userProfileId = getProfileIdFromPayload(token);
        if (id.equals(userProfileId)) {
            profileService.update(id, profileRequest);
        } else {
            throw new NotAuthorizedException("Unauthorized!");
        }
    }

    @GetMapping(path = "/profiles/{id}")
    public ProfileResponse read(HttpServletRequest request,
                                @PathVariable(name = "id") UUID id,
                                @RequestHeader(name = "x-jwt-token") String token) {
        System.out.println("!!! HEADERS");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            log.info(key + " " + value);
        }

        final UUID userProfileId = getProfileIdFromPayload(token);
        log.info("extracted profileId: " + userProfileId);

        if (id.equals(userProfileId)) {
            return profileService.read(userProfileId);
        } else {
            throw new NotAuthorizedException("Unauthorized!");
        }
    }

    @GetMapping(path = "/profiles/info/{id}")
    public ProfileResponse getInfo(HttpServletRequest request,
                                   @PathVariable(name = "id") UUID id) {
        System.out.println("!!! HEADERS");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            log.info(key + " " + value);
        }

        return profileService.read(id);
    }
}
