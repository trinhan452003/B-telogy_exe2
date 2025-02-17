package biteology.project.config;

import java.util.List;


public class SecurityConstants {
    public static final List<String> PUBLIC_URIS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/verifyAccount",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );


}
