package com.groom.demo.domain.book;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findByUserId(Long userId);

    int countByUserId(Long userId);
}
