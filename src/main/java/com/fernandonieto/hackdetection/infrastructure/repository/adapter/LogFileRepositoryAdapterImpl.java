package com.fernandonieto.hackdetection.infrastructure.repository.adapter;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.infrastructure.repository.adapter.mapper.LogFileRepositoryMapper;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogFileRepositoryAdapterImpl implements LogFileRepositoryAdapter {

    @NonNull
    private LogFileRepositoryMapper mapper;

    @Override
    public LogFile adapt(final LogFileEntity entity) {
        return mapper.toModel(entity);
    }

    @Override
    public LogFileEntity adapt(final LogFile model) {
        return mapper.toEntity(model);
    }
}
