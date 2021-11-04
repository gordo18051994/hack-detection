package com.fernandonieto.hackdetection.infrastructure.repository;

import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFileJpaRepository extends MongoRepository<LogFileEntity, String> {
}
