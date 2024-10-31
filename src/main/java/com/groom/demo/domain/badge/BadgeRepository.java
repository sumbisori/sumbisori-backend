package com.groom.demo.domain.badge;

import com.groom.demo.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Book, Long> {
}
