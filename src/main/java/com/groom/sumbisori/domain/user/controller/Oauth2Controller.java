package com.groom.sumbisori.domain.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller implements Oauth2Api {
    @Value("${custom.oauth2.kakao.logout-uri}")
    private String logoutUrl;

    @GetMapping("/authorization/kakao/logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect(logoutUrl);
    }
}
