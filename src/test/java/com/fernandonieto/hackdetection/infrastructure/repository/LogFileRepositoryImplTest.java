package com.fernandonieto.hackdetection.infrastructure.repository;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.domain.model.LoginAction;
import com.fernandonieto.hackdetection.infrastructure.repository.adapter.LogFileRepositoryAdapter;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LogFileRepositoryImplTest {

    private static final String IP = "ip";
    private static final Integer INC = 1;
    private static final String IP1 = "1";
    private static final int COUNT = 1;
    private static final String USERNAME = "USERNAME";
    private static final int COUNT_2 = 2;
    @InjectMocks
    private LogFileRepositoryImpl fileRepository;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private FindAndModifyOptions findAndModifyOptions;
    @Mock
    private Update update;
    @Mock
    private LogFileRepositoryAdapter adapter;

    @Test
    public void should_save() {
        //given
        final LogFile request = newLogFile();
        final LogFile response = newLogFile();
        final LogFileEntity entity = myEntity();
        final Query query = new Query(Criteria.where(IP).is(request.getIp()));
        when(mongoTemplate.exists(query, entity.getClass())).thenReturn(false);
        when(adapter.adapt(request)).thenReturn(entity);
        when(mongoTemplate.save(entity)).thenReturn(entity);
        when(adapter.adapt(entity)).thenReturn(response);
        //when
        fileRepository.saveLogin(request);
        //then
        LogFile logFile = fileRepository.saveLogin(new LogFile());
        assertThat(response.getIp()).isEqualTo(request.getIp());
        assertThat(response.getCount()).isEqualTo(request.getCount());
    }

    private LogFile newLogFile() {
        return new LogFile(IP1, LocalDateTime.now(), LoginAction.SIGNIN_FAILURE, USERNAME, COUNT);
    }

    private LogFileEntity myEntity() {
        return new LogFileEntity(IP1, LocalDateTime.now(), LoginAction.SIGNIN_FAILURE.name(), USERNAME, COUNT);
    }
}
