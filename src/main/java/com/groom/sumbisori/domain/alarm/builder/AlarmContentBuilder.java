package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.AlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;

public interface AlarmContentBuilder<T extends AlarmContext> {
    AlarmType getType();

    AlarmContent build(T context);
}
