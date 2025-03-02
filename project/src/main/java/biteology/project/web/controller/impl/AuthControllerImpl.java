package biteology.project.web.controller.impl;

import biteology.project.dto.request.LoginRegisterRequest;
import biteology.project.dto.response.LoginResponse;
import biteology.project.dto.response.Response;
import biteology.project.entity.Account;
import biteology.project.service.AuthenticationService;
import biteology.project.web.controller.AuthController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "AUTH API", description = "APIs for authen and author")
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;


    @Operation(summary = "Register a new account", description = "Create a new account and also create an user profile for that account ")
    @Override
    public Response<Account> register(LoginRegisterRequest registerRequest) {
        log.info("=================Request Register For User : {} =================", registerRequest.getEmail());

        return Response.created(authenticationService.register(registerRequest));
    }

    @Override
    @Operation(summary = "Login ", description = "Login and response the token")
    public Response<LoginResponse> login(LoginRegisterRequest loginRequest) {
        log.info("=================Request Login For User : {} =================", loginRequest.getEmail());
        return Response.ok(authenticationService.login(loginRequest));
    }
    @Operation(summary = "Register as a Doctor", description = "Create a new account and also create an user profile for that account as Role DOCTOR ")
    @Override
    public Response<?> registerAsDoctor(LoginRegisterRequest registerRequest) {
        log.info("=================Request Register Doctor For User : {} =================", registerRequest.getEmail());

        return Response.created(authenticationService.registerAsDoctor(registerRequest));
    }
}
