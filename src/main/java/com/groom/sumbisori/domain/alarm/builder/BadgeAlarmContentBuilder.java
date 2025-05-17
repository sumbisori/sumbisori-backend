package com.groom.sumbisori.domain.alarm.builder;

import com.groom.sumbisori.domain.alarm.context.BadgeAlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import com.groom.sumbisori.domain.badge.entity.BadgeRankLevel;
import com.groom.sumbisori.domain.badge.entity.BadgeType;
import org.springframework.stereotype.Component;

@Component
public class BadgeAlarmContentBuilder implements AlarmContentBuilder<BadgeAlarmContext> {
    private final static String MESSAGE_TEMPLATE = "%s %s배지를 획득했습니다.";
    private final static String LINK_TEMPLATE = "/my-page/badge?badge-id=%s";

    @Override
    public AlarmType getType() {
        return AlarmType.BADGE_ACQUIRED;
    }

    @Override
    public AlarmContent build(BadgeAlarmContext ctx) {
        String message = String.format(MESSAGE_TEMPLATE, ctx.name(), getBadgeLevelLabel(ctx.badgeType(), ctx.level()));
        String link = String.format(LINK_TEMPLATE, ctx.badgeId());
        return AlarmContent.of(message, link);
    }

    private String getBadgeLevelLabel(BadgeType badgeType, int level) {
        if (badgeType == BadgeType.RANKED) {
            return BadgeRankLevel.getLabel(level);
        }
        return "";
    }
}
