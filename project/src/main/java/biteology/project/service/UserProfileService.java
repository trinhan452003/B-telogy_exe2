package biteology.project.service;

import biteology.project.dto.request.UserProfileDTORequest;
import biteology.project.entity.UserProfile;

public interface UserProfileService {
    UserProfile getUserProfile();
    UserProfile updateUserProfile(UserProfileDTORequest userProfile);
}
