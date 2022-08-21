package ru.gur.archprofiles.service;

import ru.gur.archprofiles.web.ProfileRequest;
import ru.gur.archprofiles.web.ProfileResponse;

import java.util.UUID;

public interface ProfileService {

    UUID create(String email);

    void update(UUID id, ProfileRequest profile);

    ProfileResponse read(UUID id);
}