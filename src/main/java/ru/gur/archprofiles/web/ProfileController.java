package ru.gur.archprofiles.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archprofiles.service.ProfileService;

import java.util.UUID;

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

//
//    @PatchMapping(path = "/profiles/{id}")
//    public void update(@PathVariable(name = "id") String id, @RequestBody Profile profile, @RequestHeader("Authorization") String token) {
//        validate(token, id);
//        profileService.update(id, profile);
//    }
//
//    @GetMapping(path = "/profiles/{id}")
//    public Profile read(@PathVariable(name = "id") String id, @RequestHeader(name = "Authorization") String token) {
//        validate(token, id);
//        return profileService.read(id);
//    }
//
//    private String getEmailFromToken(HttpServletRequest request) {
//        String token = request.getHeader("x-jwt-token");//
//        Assert.hasText(token, "x-jwt-token must not be blank");
//
//        String[] chunks = token.split("\\.");
//
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//
//        String payload = new String(decoder.decode(chunks[1]));
//        System.out.println("!p" + payload);
//
//        try {
//            Map<String, Object> mapping = new ObjectMapper().readValue(payload, HashMap.class);
//            return mapping.get("email").toString();
//        } catch (Exception e) {
//            //do nothing
//        }
//        return null;
//    }
}
