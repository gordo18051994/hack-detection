package com.fernandonieto.hackdetection.infrastructure.repository.adapter;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;

public interface LogFileRepositoryAdapter {

    LogFile adapt(LogFileEntity entity);

    LogFileEntity adapt(LogFile model);

}
