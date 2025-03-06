package com.groom.sumbisori.domain.user.repository;

import com.groom.sumbisori.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderIdAndProviderType(String providerId, String providerType);

    Optional<User> findByNickname(String nickname);
}
