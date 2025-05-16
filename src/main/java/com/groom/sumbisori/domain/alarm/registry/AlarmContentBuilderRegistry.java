package com.groom.sumbisori.domain.alarm.registry;

import com.groom.sumbisori.domain.alarm.builder.AlarmContentBuilder;
import com.groom.sumbisori.domain.alarm.context.AlarmContext;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AlarmContentBuilderRegistry {
    private final Map<AlarmType, AlarmContentBuilder<?>> builderMap;

    public AlarmContentBuilderRegistry(List<AlarmContentBuilder<?>> builders) {
        this.builderMap = builders.stream()
                .collect(Collectors.toMap(AlarmContentBuilder::getType, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public <T extends AlarmContext> AlarmContentBuilder<T> get(AlarmType type) {
        return (AlarmContentBuilder<T>) builderMap.get(type);
    }
}
