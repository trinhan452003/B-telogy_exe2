package biteology.project.web.controller;


import biteology.project.dto.request.LoginRegisterRequest;
import biteology.project.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/register")
    Response<?> register(@Valid @RequestBody LoginRegisterRequest registerRequestRequest);

    @PostMapping("/login")
    Response<?> login(@Valid @RequestBody LoginRegisterRequest loginRequest);

}
