package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.ExperienceAlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import org.springframework.stereotype.Component;

@Component
public class ExperienceAlarmContentBuilder implements AlarmContentBuilder<ExperienceAlarmContext> {
    @Override
    public AlarmType getType() {
        return AlarmType.EXPERIENCE_COMPLETED;
    }

    @Override
    public AlarmContent build(ExperienceAlarmContext context) {
        return null;
    }
}
