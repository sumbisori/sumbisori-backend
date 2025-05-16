package com.groom.sumbisori.domain.alarm.repository;

import com.groom.sumbisori.domain.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
