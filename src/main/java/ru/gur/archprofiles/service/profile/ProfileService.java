package ru.gur.archprofiles.service.profile;

import ru.gur.archprofiles.web.profile.request.ProfileRequest;
import ru.gur.archprofiles.web.profile.response.ProfileResponse;

import java.util.UUID;

public interface ProfileService {

    UUID create(String email);

    void update(UUID id, ProfileRequest profile);

    ProfileResponse read(UUID id);
}