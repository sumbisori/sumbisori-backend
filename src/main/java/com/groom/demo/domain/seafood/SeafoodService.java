package com.groom.demo.domain.seafood;

import com.groom.demo.domain.book.Book;
import com.groom.demo.domain.book.BookRepository;
import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodService {
    private final BookRepository bookRepository;

    public List<MySeafoodDto> mySeafoodList(Long userId) {
        // 사용자의 Book 목록을 가져오되, 없으면 빈 리스트를 반환합니다.
        List<Book> books = bookRepository.findByUserId(userId).orElse(Collections.emptyList());

        // books 리스트를 MySeafood로 변환하는 로직을 추가합니다.
        List<MySeafoodDto> mySeafoods = books.stream()
                .map(book -> {
                    return new MySeafoodDto(book.getSeafood(), book.getSeafood().getName(),
                                     book.getSeafood().getDescription(), book.getCreatedAt());
                })
                .collect(Collectors.toList());
        return mySeafoods;
    }
}
