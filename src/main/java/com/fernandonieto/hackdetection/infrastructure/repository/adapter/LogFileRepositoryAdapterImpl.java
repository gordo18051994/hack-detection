package com.fernandonieto.hackdetection.infrastructure.repository.adapter;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.infrastructure.repository.adapter.mapper.LogFileRepositoryMapper;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.springframework.stereotype.Component;

@Component
public class LogFileRepositoryAdapterImpl implements LogFileRepositoryAdapter {

    private final LogFileRepositoryMapper mapper = LogFileRepositoryMapper.INSTANCE;

    @Override
    public LogFile adapt(final LogFileEntity entity) {
        return mapper.toModel(entity);
    }

    @Override
    public LogFileEntity adapt(final LogFile model) {
        return mapper.toEntity(model);
    }
}
