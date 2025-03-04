package biteology.project.service.impl;


import biteology.project.common.utils.ContextHolderUtils;
import biteology.project.dto.request.UserProfileDTORequest;
import biteology.project.entity.Account;
import biteology.project.entity.UserProfile;
import biteology.project.mapper.UserProfileMapper;
import biteology.project.repository.UserProfileRepository;
import biteology.project.service.UserProfileService;
import biteology.project.web.error.ExceptionDefine.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserProfileImpl implements UserProfileService {

    UserProfileRepository profileRepository;
    UserProfileMapper userProfileMapper;
    @Override
    public UserProfile getUserProfile() {
        Account currentAccount = ContextHolderUtils.getContext();
        if(currentAccount != null) {
           return currentAccount.getProfile();
        }
        throw new NotFoundException("UserProfile not found");
    }

    @Override
    public UserProfile updateUserProfile(UserProfileDTORequest userProfile) {
        Account currentUser = ContextHolderUtils.getContext();
        UserProfile userProfileToUpdate = currentUser.getProfile();
        userProfileMapper.partialUpdate(userProfileToUpdate, userProfile);
        return profileRepository.save(userProfileToUpdate);
    }
}
