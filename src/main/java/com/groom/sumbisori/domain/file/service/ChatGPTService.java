package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.SeafoodRecognitionResponse;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import java.net.URL;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGPTService {
    private static final String PROMPT_TEXT = """
            Identify seafood and marine debris in the image.
            Return **only** the identified items from the following list along with their count.
            The response **must be a plain JSON array** without any metadata, schema, or additional fields.
            Use **"detail": "low"** in the image request to optimize for speed.
            
            ## List of Recognizable Items
            1. 전복 (Abalone), 2. 성게 (SeaUrchin), 3. 조개 (Clam), 4. 보말 (Omphalius), 5. 소라 (Conch)
            6. 뿔소라 (Murex), 7. 미역 (SeaMustard), 8. 멍게 (SeaSquirt), 9. 홍합 (Mussel), 10. 고둥 (Gastropods)
            11. 굴 (Oyster), 12. 문어 (Octopus), 13. 해삼 (SeaCucumber), 14. 오징어 (Squid)
            15. 그물조각 (Net), 16. 밧줄 (Rope), 17. 비닐 (Vinyl), 18. 물병 (WaterBottle).
            """;

    private final ChatClient chatClient;

    public List<SeafoodRecognitionResponse> analyzeSeafoodImage(URL imageUrl) {
        try {
            UserMessage userMessage = new UserMessage(PROMPT_TEXT, new Media(MimeTypeUtils.IMAGE_JPEG, imageUrl));
            Prompt prompt = new Prompt(userMessage);

            List<SeafoodRecognitionResponse> responses = chatClient.prompt(prompt)
                    .call()
                    .entity(new ParameterizedTypeReference<List<SeafoodRecognitionResponse>>() {
                    });

            if (responses == null || responses.isEmpty()) { // 이미지가 해당 항목을 포함하지 않을 경우 혹은 AI가 인식하지 못할 경우
                return List.of();
            }
            return responses;
        } catch (Exception e) { // 이미지가 존재하지 않을 경우, AI 서버가 응답하지 않을 경우
            log.error("Unexpected error during image recognition for URL: {}. Error: {}", imageUrl, e.getMessage(), e);
            throw new FileException(FileErrorcode.CHAT_GPT_ERROR);
        }
    }
}
