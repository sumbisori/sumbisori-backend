package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.BadgeAlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import org.springframework.stereotype.Component;

@Component
public class BadgeAlarmContentBuilder implements AlarmContentBuilder<BadgeAlarmContext> {

    @Override
    public AlarmType getType() {
        return AlarmType.BADGE_ACQUIRED;
    }

    @Override
    public AlarmContent build(BadgeAlarmContext ctx) {
        return null;
    }
}
