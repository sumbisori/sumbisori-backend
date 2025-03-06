package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.user.dto.common.KaKaoResponse;
import com.groom.sumbisori.domain.user.dto.common.OAuth2Response;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private OAuth2Response oAuth2Response;

    @BeforeEach
    public void setup() {
        oAuth2Response = new KaKaoResponse(Map.of(
                "id", "12345",
                "kakao_account", Map.of("email", "user@example.com"),
                "properties", Map.of("nickname", "testUser"),
                "profile", Map.of("profile_image_url", "https://example.com/profile.jpg")
        ));
    }

    @Test
    public void 동시에_여러_개의_회원가입_요청_테스트() throws InterruptedException {
        int threadCount = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    userService.createAndUpdateMember(oAuth2Response, "kakao");
                    successCount.incrementAndGet();
                } catch (OAuth2AuthenticationException e) {
                    failureCount.incrementAndGet();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(1);
        assertThat(failureCount.get()).isEqualTo(4);
    }
}
