package com.fernandonieto.hackdetection.infrastructure.repository;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.domain.port.output.LogFileRepository;
import com.fernandonieto.hackdetection.infrastructure.repository.adapter.LogFileRepositoryAdapter;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class LogFileRepositoryImpl implements LogFileRepository {

    private static final String IP = "ip";
    private static final String DATE = "date";
    private static final String COUNT = "count";
    private static final int INC = 1;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private LogFileRepositoryAdapter adapter;

    @Override
    public LogFile saveLogin(final LogFile model) {
        final Query query = new Query(Criteria.where(IP).is(model.getIp()));
        final Update update = new Update().inc(COUNT, INC);
        final FindAndModifyOptions findAndModifyOptions = FindAndModifyOptions.options().returnNew(true).upsert(true);
        final LogFileEntity entity = adapter.adapt(model);
        if (!mongoTemplate.exists(query, LogFileEntity.class)) {
            final LogFileEntity saved = mongoTemplate.save(entity);
            return adapter.adapt(saved);
        }
        final LogFileEntity updated = mongoTemplate.findAndModify(query, update, findAndModifyOptions, LogFileEntity.class);
        return adapter.adapt(updated);
    }

    @Override
    public void deleteOlderLogins() {
        final LocalDateTime filter = LocalDateTime.now().minusMinutes(5L);
        final Query query = new Query(Criteria.where(DATE).lte(filter));
        mongoTemplate.findAllAndRemove(query, LogFileEntity.class);

    }
}
