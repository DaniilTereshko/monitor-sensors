package com.tdi.sensorservice.security;

import com.tdi.sensorservice.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private static final String ADMIN = "ADMIN";
    private static final String VIEWER = "VIEWER";
    private static final String OPEN_API_URI = "/swagger-ui/**";
    private static final String DOC_PATH_URI = "/v3/api-docs/**";
    private static final String SENSOR_API = "/api/v1/sensor";
    private static final String SENSOR_API_ID = "/api/v1/sensor/**";
    private static final String[] PUBLIC_ENDPOINTS = {OPEN_API_URI, DOC_PATH_URI};

    private final JwtFilter jwtFilter;
    @Value("${swagger.url}")
    private String SWAGGER_URL;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configure -> configure.authenticationEntryPoint(
                                (request, response, ex) -> response.setStatus(HttpStatus.UNAUTHORIZED.value()))
                        .accessDeniedHandler(
                                (request, response, ex) -> response.setStatus(HttpStatus.FORBIDDEN.value())
                        )
                )
                .authorizeHttpRequests(configure -> configure
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, SENSOR_API_ID).hasAnyRole(ADMIN, VIEWER)
                        .requestMatchers(HttpMethod.POST, SENSOR_API).hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT, SENSOR_API).hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE, SENSOR_API_ID).hasRole(ADMIN)
                        .anyRequest().authenticated())
                .anonymous(AnonymousConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(SWAGGER_URL)
                .allowedMethods("*");
    }

}