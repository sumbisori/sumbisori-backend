package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.ExperienceAlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import org.springframework.stereotype.Component;

@Component
public class ExperienceAlarmContentBuilder implements AlarmContentBuilder<ExperienceAlarmContext> {
    private final static String MESSAGE_TEMPLATE = "일지 작성이 완료되었습니다.";
    private final static String LINK_TEMPLATE = "/journals/%s";

    @Override
    public AlarmType getType() {
        return AlarmType.EXPERIENCE_COMPLETED;
    }

    @Override
    public AlarmContent build(ExperienceAlarmContext context) {
        String message = MESSAGE_TEMPLATE;
        String link = String.format(LINK_TEMPLATE, context.experienceId());
        return AlarmContent.of(message, link);
    }
}
