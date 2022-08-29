package ru.gur.archprofiles.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gur.archprofiles.entity.ProfileEntity;
import ru.gur.archprofiles.exception.ProfileNotFoundException;
import ru.gur.archprofiles.persistance.ProfileRepository;
import ru.gur.archprofiles.web.profile.request.ProfileRequest;
import ru.gur.archprofiles.web.profile.response.ProfileResponse;

import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public UUID create(String email) {
        final ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(email);

        profileRepository.save(profileEntity);

        return profileEntity.getId();
    }

    @Override
    @Transactional
    public void update(final UUID id, final ProfileRequest profile) {
        final ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile not found!"));
        profileEntity.setFullName(profile.getFullName());
        profileEntity.setAge(profile.getAge());
    }

    @Override
    public ProfileResponse read(final UUID id) {
        final ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile not found!"));

        return ProfileResponse.builder()
                .email(profileEntity.getEmail())
                .age(profileEntity.getAge())
                .fullName(profileEntity.getFullName())
                .build();
    }
}
