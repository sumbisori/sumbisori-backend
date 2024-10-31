package com.groom.demo.domain.seafood;

import com.groom.demo.domain.book.Book;
import com.groom.demo.domain.book.BookRepository;
import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeafoodService {
    private final BookRepository bookRepository;

    public List<MySeafoodDto> mySeafoodList(Long userId) {
        // 사용자의 Book 목록을 가져오되, 없으면 빈 리스트를 반환합니다.
        List<Book> books = bookRepository.findByUserId(userId).orElse(Collections.emptyList());

        // 각 Seafood 종류별 개수를 맵으로 계산합니다.
        Map<Seafood, Long> seafoodCountMap = books.stream()
                .collect(Collectors.groupingBy(Book::getSeafood, Collectors.counting()));

        // 각 Seafood 종류별 가장 오래된 생성 날짜를 맵으로 계산합니다.
        Map<Seafood, Optional<Book>> oldestBookMap = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getSeafood,
                        Collectors.minBy(Comparator.comparing(Book::getCreatedAt))
                ));

        // 모든 Seafood 항목에 대해 사용자가 가진 개수와 가장 오래된 생성 날짜로 MySeafoodDto를 생성합니다.
        List<MySeafoodDto> mySeafoods = Arrays.stream(Seafood.values())
                .map(seafood -> new MySeafoodDto(
                        seafood,
                        seafood.getName(),
                        seafood.getEnglishName(),
                        seafood.getDescription(),
                        oldestBookMap.getOrDefault(seafood, Optional.empty())
                                .map(Book::getCreatedAt)
                                .orElse(null), // 해당 Seafood의 가장 오래된 Book의 생성 날짜를 가져옴
                        seafoodCountMap.getOrDefault(seafood, 0L).intValue() // 존재하지 않으면 0으로 설정
                ))
                .collect(Collectors.toList());

        return mySeafoods;
    }

    @Transactional
    public void createSeafood(Long userId, SeafoodRequest request) {
        int count = request.getCount();
        for (int i = 0; i < count; i++) {
            Book book = Book.builder()
                    .userId(userId)
                    .seafood(request.getSeafood())
                    .build();
            bookRepository.save(book);
        }
    }
}
