package biteology.project.web.controller.impl;

import biteology.project.dto.request.LoginRegisterRequest;
import biteology.project.dto.response.LoginResponse;
import biteology.project.dto.response.Response;
import biteology.project.entity.Account;
import biteology.project.service.AuthenticationService;
import biteology.project.web.controller.AuthController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;

    @Override
    public Response<Account> register(LoginRegisterRequest registerRequest) {
        log.info("=================Request Register For User : {} =================", registerRequest.getEmail());

        return Response.created(authenticationService.register(registerRequest));
    }

    @Override
    public Response<LoginResponse> login(LoginRegisterRequest loginRequest) {
        log.info("=================Request Login For User : {} =================", loginRequest.getEmail());
        return Response.ok(authenticationService.login(loginRequest));
    }
}
