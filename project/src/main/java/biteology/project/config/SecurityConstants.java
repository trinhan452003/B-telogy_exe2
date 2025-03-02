package biteology.project.config;

import java.util.List;


public class SecurityConstants {
    public static final List<String> PUBLIC_URIS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/registerAsDoctor",
            "/api/auth/verifyAccount",
            "/swagger-ui/*",
            "/api/disease",
            "/v3/api-docs",
            "/v3/api-docs/*",
            "/api/disease/.*/foods",
            "/api/food",
            "/upload"
    );


}
