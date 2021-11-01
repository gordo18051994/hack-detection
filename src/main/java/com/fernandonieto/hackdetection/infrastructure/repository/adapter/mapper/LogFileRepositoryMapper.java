package com.fernandonieto.hackdetection.infrastructure.repository.adapter.mapper;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LogFileRepositoryMapper {

    LogFileRepositoryMapper INSTANCE = Mappers.getMapper(LogFileRepositoryMapper.class);

    LogFile toModel(LogFileEntity entity);

    LogFileEntity toEntity(LogFile entity);
}
