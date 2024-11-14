package com.groom.sumbisori.common.util;

import com.groom.sumbisori.domain.token.entity.TokenType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
public class CookieUtil {

    @Value("${client.cookie.domain}")
    private String cookieDomain;

    public void createTokenCookie(HttpServletResponse response, String token, TokenType tokenType) {
        ResponseCookie cookie = ResponseCookie.from(tokenType.name(), token)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .domain(cookieDomain)
                .maxAge(tokenType.getExpirationTime())
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .domain(cookieDomain)
                .maxAge(maxAge)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public void expireCookie(HttpServletResponse response, String cookieName) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, null)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .domain(cookieDomain)
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
