package com.groom.sumbisori.domain.user.oauth2;

import static com.groom.sumbisori.common.util.CookieUtil.createTokenCookie;

import com.groom.sumbisori.common.filter.HttpCookieOAuth2AuthorizationRequestRepository;
import com.groom.sumbisori.domain.token.entity.TokenType;
import com.groom.sumbisori.domain.token.service.TokenService;
import com.groom.sumbisori.domain.user.dto.CustomUserDetails;
import com.groom.sumbisori.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final String REDIRECT_URL_SUCCESS = "/home";

    @Value("${client.url}")
    private String clientUrl;

    private final TokenService tokenService;
    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        authorizationRequestRepository.removeAuthorizationRequestCookies(response);
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        String refresh = tokenService.createAccessToken(user);
        createTokenCookie(response, refresh, TokenType.ACCESS);
        response.sendRedirect(clientUrl + REDIRECT_URL_SUCCESS);
        log.info("소셜로그인 성공 user ID: {}", user.getId());
    }
}
