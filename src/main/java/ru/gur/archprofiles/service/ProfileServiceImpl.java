package ru.gur.archprofiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gur.archprofiles.entity.ProfileEntity;
import ru.gur.archprofiles.exception.ProfileNotFoundException;
import ru.gur.archprofiles.persistance.ProfileRepository;
import ru.gur.archprofiles.web.ProfileDto;

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
    public void update(final UUID id, final ProfileDto profile) {
//        ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        profileEntity.setFullName(profile.getFullName());
//        profileEntity.setCitizenship(profile.getCitizenship());
//        profileEntity.setAge(profile.getAge());
    }

    @Override
    public ProfileDto read(final UUID id) {
        final ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);

        return ProfileDto.builder()
                .age(profileEntity.getAge())
                .fullName(profileEntity.getFullName())
                .build();
    }
}
