package biteology.project.config;

import biteology.project.config.filter.AuthenticationFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    AuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        // ✅ Cho phép truy cập Swagger UI và API docs mà không cần xác thực
                        .requestMatchers(mvc.pattern("/swagger-ui/**")).permitAll()
                        .requestMatchers(mvc.pattern("/swagger-ui.html")).permitAll()
                        .requestMatchers(mvc.pattern("/v3/api-docs/**")).permitAll()

                        // ✅ Các API công khai
                        .requestMatchers(SecurityConstants.PUBLIC_URIS.toArray(String[]::new)).permitAll()
                        .requestMatchers(mvc.pattern("/api/disease/{id}/foods")).permitAll()

                        // 🔒 API cần quyền "DOCTOR"
                        .requestMatchers(HttpMethod.POST, "/api/disease/createDisease").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/disease/deleteDisease").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/food/deleteFoods").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.POST, "/api/food/createFood").hasRole("DOCTOR")
                        .requestMatchers(mvc.pattern("/api/food/**/assignDiseaseForAFood")).hasRole("DOCTOR")

                        // 🔒 Còn lại phải xác thực
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }


}