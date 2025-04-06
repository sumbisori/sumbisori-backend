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
            Return only the identified items from the following list along with their count.
            The response must be a plain JSON array without any metadata, schema, or additional fields.
            Use "detail": "low" in the image request to optimize for speed.
            
            Each item in the response must strictly follow this JSON structure:
            {
              "seafoodId": 1,
              "koreanName": "전복",
              "englishName": "Abalone",
              "count": 1
            }
            
            Do not switch or mix koreanName and englishName fields.
            
            ## List of Recognizable Items (with fixed IDs)
            1. seafoodId: 1, koreanName: "전복", englishName: "Abalone"
            2. seafoodId: 2, koreanName: "성게", englishName: "SeaUrchin"
            3. seafoodId: 3, koreanName: "조개", englishName: "Clam"
            4. seafoodId: 6, koreanName: "뿔소라", englishName: "Murex"
            5. seafoodId: 7, koreanName: "미역", englishName: "SeaMustard"
            6. seafoodId: 8, koreanName: "멍게", englishName: "SeaSquirt"
            7. seafoodId: 9, koreanName: "홍합", englishName: "Mussel"
            8. seafoodId: 10, koreanName: "고둥", englishName: "Gastropods"
            9. seafoodId: 11, koreanName: "굴", englishName: "Oyster"
            10. seafoodId: 12, koreanName: "문어", englishName: "Octopus"
            11. seafoodId: 13, koreanName: "해삼", englishName: "SeaCucumber"
            12. seafoodId: 14, koreanName: "오징어", englishName: "Squid"
            13. seafoodId: 15, koreanName: "그물조각", englishName: "Net"
            14. seafoodId: 16, koreanName: "밧줄", englishName: "Rope"
            15. seafoodId: 17, koreanName: "비닐", englishName: "Vinyl"
            16. seafoodId: 18, koreanName: "물병", englishName: "WaterBottle"
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
