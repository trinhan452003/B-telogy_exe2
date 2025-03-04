package biteology.project.web.controller.impl;


import biteology.project.dto.request.UserProfileDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.entity.UserProfile;
import biteology.project.service.UserProfileService;
import biteology.project.web.controller.UserProfileController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Profile API", description = "APIs for managing Profile")
public class UserProfileControllerImpl implements UserProfileController {

    UserProfileService userProfileService;


    @Override
    public Response<UserProfile> getUserProfile() {
        log.info("=================Request Get Profile =================");

        return Response.ok(userProfileService.getUserProfile());
    }

    @Override
    public Response<UserProfile> updateUserProfile(UserProfileDTORequest request) {
        log.info("=================Request Get Update Profile =================");
        return Response.ok(userProfileService.updateUserProfile(request));
    }


}
