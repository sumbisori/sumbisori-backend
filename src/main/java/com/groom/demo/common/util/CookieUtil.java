package com.groom.demo.common.util;

import com.groom.demo.domain.token.entity.TokenType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.http.ResponseCookie;
import org.springframework.util.SerializationUtils;

public class CookieUtil {
    public static void createTokenCookie(HttpServletResponse response, String token, TokenType tokenType) {
        ResponseCookie cookie = ResponseCookie.from(tokenType.name(), token)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(tokenType.getExpirationTime())
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
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

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        try {
            String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            StringBuilder cookieValue = new StringBuilder();
            cookieValue.append(name).append("=").append(encodedValue);
            cookieValue.append("; Path=/");
            cookieValue.append("; Max-Age=").append(maxAge);
            cookieValue.append("; HttpOnly");
            // 요청이 Secure인지 확인하여 Secure 속성 설정
            if (request.isSecure()) {
                cookieValue.append("; Secure");
                cookieValue.append("; SameSite=None");
            } else {
                // 개발 환경에서는 Secure 속성을 설정하지 않음
                cookieValue.append("; SameSite=Lax");
            }
            response.addHeader("Set-Cookie", cookieValue.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void expireCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        try {
            StringBuilder cookieValue = new StringBuilder();
            cookieValue.append(name).append("=; Path=/");
            cookieValue.append("; Max-Age=0");
            cookieValue.append("; HttpOnly");
            // 요청이 Secure인지 확인하여 Secure 속성 설정
            if (request.isSecure()) {
                cookieValue.append("; Secure");
                cookieValue.append("; SameSite=None");
            } else {
                // 개발 환경에서는 Secure 속성을 설정하지 않음
                cookieValue.append("; SameSite=Lax");
            }
            response.addHeader("Set-Cookie", cookieValue.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
