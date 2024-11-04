package com.groom.demo.domain.seafood;

import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodResponse;
import com.groom.demo.domain.seafood.repository.SeafoodQueryRepository;
import com.groom.demo.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodService {
    private final SeafoodQueryRepository seafoodQueryRepository;
    private final SeafoodRepository seafoodRepository;

    public List<SeafoodResponse> getAllSeafoods() {
        return seafoodRepository.findAll().stream()
                .map(SeafoodResponse::from)
                .toList();
    }

    public List<MySeafoodDto> mySeafoodList(Long userId) {
        return seafoodQueryRepository.findAllSeafoodCollectionStatusByUserId(userId);
    }

//    @Transactional
//    public void createSeafood(Long userId, SeafoodRequest request) {
//        int count = request.getCount();
//        for (int i = 0; i < count; i++) {
//            Book book = Book.builder()
//                    .userId(userId)
////                    .seafood(request.getSeafood())
//                    .build();
//            bookRepository.save(book);
//        }
//    }
}
