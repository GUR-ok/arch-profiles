package ru.gur.archprofiles.web.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public void update(@PathVariable(name = "id") UUID id, @RequestBody ProfileRequest profileRequest, @RequestHeader("x-jwt-token") String token) {
        final UUID userProfileId = getProfileIdFromPayload(token);
        if (id.equals(userProfileId)) {
            profileService.update(id, profileRequest);
        } else {
            throw new NotAuthorizedException("Unauthorized!");
        }
    }

    @GetMapping(path = "/profiles/{id}")
    public ProfileResponse read(@PathVariable(name = "id") UUID id, @RequestHeader(name = "x-jwt-token") String token) {
        final UUID userProfileId = getProfileIdFromPayload(token);
        log.info("extracted profileId: " + userProfileId);

        if (id.equals(userProfileId)) {
            return profileService.read(userProfileId);
        } else {
            throw new NotAuthorizedException("Unauthorized!");
        }
    }

    private UUID getProfileIdFromPayload(String tokenPayload) {
        Assert.hasText(tokenPayload, "tokenPayload must not be blank");

        log.info("auth tokenPayload: " + tokenPayload);

        final Base64.Decoder decoder = Base64.getUrlDecoder();
        final String payload = new String(decoder.decode(tokenPayload));

        log.info("payload: " + payload);

        try {
            Map<String, Object> mapping = new ObjectMapper().readValue(payload, HashMap.class);
            log.info("mapping: " + mapping);

            return UUID.fromString(mapping.get("profileId").toString());
        } catch (Exception e) {
            //do nothing
        }
        return null;
    }

    private UUID getProfileIdFromOriginalToken(String token) {
        Assert.hasText(token, "x-jwt-token must not be blank");

        log.info("auth token: " + token);

        final String[] chunks = token.split("\\.");
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        final String payload = new String(decoder.decode(chunks[1]));

        try {
            Map<String, Object> mapping = new ObjectMapper().readValue(payload, HashMap.class);
            return UUID.fromString(mapping.get("profileId").toString());
        } catch (Exception e) {
            //do nothing
        }
        return null;
    }
}
