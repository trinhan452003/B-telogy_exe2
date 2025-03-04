package biteology.project.web.controller;


import biteology.project.dto.request.UserProfileDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.entity.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/profile")
public interface UserProfileController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Response<UserProfile> getUserProfile();

    @PutMapping("/updateProfile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Response<UserProfile> updateUserProfile(
            @RequestBody final UserProfileDTORequest request);
}
