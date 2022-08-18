package ru.gur.archprofiles.service;

import ru.gur.archprofiles.web.ProfileDto;

import java.util.UUID;

public interface ProfileService {

    UUID create(String email);

    void update(UUID id, ProfileDto profile);

    ProfileDto read(UUID id);
}