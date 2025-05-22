package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.BadgeAlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import com.groom.sumbisori.domain.badge.entity.BadgeRankLevel;
import org.springframework.stereotype.Component;

@Component
public class BadgeAlarmContentBuilder implements AlarmContentBuilder<BadgeAlarmContext> {
    private static final String MESSAGE_TEMPLATE_COMMON = "%s 배지를 획득했습니다.";
    private static final String MESSAGE_TEMPLATE_RANKED = "%s %s배지를 획득했습니다.";
    private final static String LINK_TEMPLATE = "/my-page/badge?badge-id=%s";

    @Override
    public AlarmType getType() {
        return AlarmType.BADGE_ACQUIRED;
    }

    @Override
    public AlarmContent build(BadgeAlarmContext ctx) {
        String message = switch (ctx.badgeType()) {
            case BASIC, SPECIAL -> String.format(MESSAGE_TEMPLATE_COMMON, ctx.name());
            case RANKED -> String.format(
                    MESSAGE_TEMPLATE_RANKED,
                    ctx.name(),
                    BadgeRankLevel.getLabel(ctx.level())
            );
        };
        String link = String.format(LINK_TEMPLATE, ctx.badgeId());
        return AlarmContent.of(message, link);
    }
}
