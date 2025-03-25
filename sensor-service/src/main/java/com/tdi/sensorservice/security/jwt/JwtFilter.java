package com.tdi.sensorservice.security.jwt;

import com.tdi.sensorservice.security.service.JwtService;
import com.tdi.sensorservice.service.client.UserServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserServiceClient userServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!jwtService.isValidaAuthHeader(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authorizationHeader.substring(7);
        if (!jwtService.isValid(jwt, JwtType.ACCESS)) {
            filterChain.doFilter(request, response);
            return;
        }

        var authentication = getAuthentication(jwt);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(final String jwt) {
        var dto = userServiceClient.getMe("Bearer " + jwt)
                .getBody();

        if (dto != null) {
            return new UsernamePasswordAuthenticationToken(
                    dto,
                    null,
                    dto.getRole() == null ?
                            List.of() :
                            List.of(new SimpleGrantedAuthority("ROLE_" + dto.getRole().getAuthority()))
            );
        }

        return null;
    }

}