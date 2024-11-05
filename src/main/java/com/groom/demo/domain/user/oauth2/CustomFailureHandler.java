package com.groom.demo.domain.user.oauth2;

import com.groom.demo.common.filter.HttpCookieOAuth2AuthorizationRequestRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final String REDIRECT_URL_FAILURE = "/login?status=failure";

    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestRepository;

    @Value("${client.url}")
    private String clientUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = exception.getMessage();

        // 추가적인 정보 출력
        log.error("Social login failure - Request URI: {}, User-Agent: {}, Error: {}",
                request.getRequestURI(),
                request.getHeader("User-Agent"),
                errorMessage);

        if (exception.getCause() != null) {
            log.error("Root cause: {}", exception.getCause().getMessage());
        }

        // 다른 정보들 추가
        log.debug("Full error details", exception);

        response.sendRedirect("/login?error"); // 원하는 리다이렉션 경로 설정
    }
}
