package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.sumbisori.domain.reservation.repository.ReservationRepository;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDeleteService {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final SeafoodCollectionRepository seafoodCollectionRepository;

    @Transactional
    public void delete(User user) {
        reservationRepository.deleteByUserId(user.getId());
        seafoodCollectionRepository.deleteByUserId(user.getId());
        userRepository.delete(user);
    }
}
