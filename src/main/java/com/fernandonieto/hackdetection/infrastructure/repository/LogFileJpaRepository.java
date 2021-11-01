package com.fernandonieto.hackdetection.infrastructure.repository;

import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogFileJpaRepository extends MongoRepository<LogFileEntity, String> {
}
