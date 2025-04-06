package com.groom.sumbisori.domain.user.repository;

import com.groom.sumbisori.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderIdAndProviderType(String providerId, String providerType);

    @Query("SELECT u.nickname FROM User u WHERE u.id = :userId")
    String findNicknameById(@Param("userId") Long userId);
}
