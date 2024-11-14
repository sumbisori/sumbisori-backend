package com.groom.sumbisori.common.filter;

import com.groom.sumbisori.common.util.CookieUtil;
import com.groom.sumbisori.common.util.JWTUtil;
import com.groom.sumbisori.domain.token.entity.TokenType;
import com.groom.sumbisori.domain.user.dto.CustomUserDetails;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.entity.User.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie accessCookie = cookieUtil.getCookieByName(request, TokenType.ACCESS.name());
        if (accessCookie != null) {
            String accessToken = accessCookie.getValue();
            jwtUtil.validateToken(accessToken, TokenType.ACCESS);
            setAuthentication(accessToken);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
            return;
        }
    }

    private void setAuthentication(String token) {
        Long id = jwtUtil.getId(token);
        String role = jwtUtil.getRole(token);
        User user = User.builder()
                .id(id)
                .userRole(UserRole.valueOf(role))
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
