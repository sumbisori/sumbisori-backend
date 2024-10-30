package com.groom.demo.domain.user.repository;

import com.groom.demo.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderTypeAndProviderId(String providerType, String providerId);
}
