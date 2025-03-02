package biteology.project.service.impl;

import biteology.project.common.utils.JwtUtils;
import biteology.project.config.SecurityProperties;
import biteology.project.dto.request.LoginRegisterRequest;
import biteology.project.dto.response.LoginResponse;
import biteology.project.entity.Account;
import biteology.project.entity.Enum.AccountRole;
import biteology.project.entity.Enum.AccountStatus;
import biteology.project.entity.HealthRecord;
import biteology.project.entity.UserProfile;
import biteology.project.repository.AccountRepository;
import biteology.project.service.AuthenticationService;
import biteology.project.web.error.ExceptionDefine.AuthenticationException;
import biteology.project.web.error.ExceptionDefine.BusinessException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {

    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    SecurityProperties securityProperties;

    @Override
    @Transactional
    public Account register(LoginRegisterRequest request) {
        if(accountRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Username already exists");
        }
        final var newAccount = Account.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(AccountRole.USER)
                .status(AccountStatus.INACTIVE)
                .uuid(UUID.randomUUID().toString())
                .build();


        //userProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setAccount(newAccount);
        newAccount.setProfile(userProfile);

        accountRepository.save(newAccount);
        return newAccount;
    }

    @Override
    public Account registerAsDoctor(LoginRegisterRequest request) {
        if(accountRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Username already exists");
        }
        final var newAccount = Account.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(AccountRole.DOCTOR)
                .status(AccountStatus.ACTIVE)
                .uuid(UUID.randomUUID().toString())
                .build();


        //userProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setAccount(newAccount);
        newAccount.setProfile(userProfile);

        accountRepository.save(newAccount);
        return newAccount;
    }

    @Override
    public LoginResponse login(LoginRegisterRequest request) {
        final var account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Email not found"));

        if(!passwordEncoder.matches(request.getPassword(), account.getPasswordHash())){
            throw new AuthenticationException(HttpStatus.OK.getReasonPhrase(), "Your information does not match our password");
        }

        final var token = JwtUtils.generateJwtToken(account, securityProperties.getJwtSecret(), securityProperties.getJwtExpiration());
        return new LoginResponse(token);

    }
}
