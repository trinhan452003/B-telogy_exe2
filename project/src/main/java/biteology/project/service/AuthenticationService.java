package biteology.project.service;

import biteology.project.dto.request.LoginRegisterRequest;
import biteology.project.dto.response.LoginResponse;
import biteology.project.entity.Account;

public interface AuthenticationService {

    Account register(LoginRegisterRequest request);
    LoginResponse login(LoginRegisterRequest request);
}
