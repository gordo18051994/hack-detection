package com.fernandonieto.hackdetection;

import com.fernandonieto.hackdetection.domain.port.input.HackerDetector;
import com.fernandonieto.hackdetection.infrastructure.repository.entity.LogFileEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HackDetectionApplicationTests {

    private static final String LOG_FILE_ENTITY = "logFileEntity";
    private static final String EMPTY = "";
    private static final String IP = "80.238.9.179";
    private static final String SIGNIN_FAILURE = "SIGNIN_FAILURE";
    private static final String ANDY_BRANNING = "Andy.Branning";
    private static final String SIGNIN_SUCCESS = "SIGNIN_SUCCESS";
    private static final String COMMA = ",";
    private static final String STRING_ARG = "%s";
    private static final String IP_KEY = "ip";
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HackerDetector hackerDetector;
    private List<String> logins = new ArrayList<>();

    @BeforeEach
    public void setup() {
        mongoTemplate.dropCollection(LOG_FILE_ENTITY);
        int count = 1;
        while (count <= 5) {
            logins.add(String.format(IP + COMMA + STRING_ARG + COMMA + SIGNIN_FAILURE + COMMA + ANDY_BRANNING,
                    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
            count++;
        }
    }

    @Test
    @Order(1)
    void should_return_suspicious_ip() {
        final List<String> ips = new ArrayList<>();
        logins.forEach(login -> {
            String s = hackerDetector.parseLine(login);
            if (s != null && !s.isEmpty()) {
                ips.add(s);
            }
        });
        Assertions.assertThat(ips.get(0)).isEqualTo(IP);

    }

    @Test
    @Order(2)
    void sign_success_should_not_save_it() {
        final String logToDelete = String.format(IP + COMMA + STRING_ARG + COMMA + SIGNIN_SUCCESS + COMMA + ANDY_BRANNING,
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        final Query findByIp = new Query(Criteria.where(IP_KEY).is(IP));
        String ip = hackerDetector.parseLine(logToDelete);
        List<LogFileEntity> fileEntities = mongoTemplate.find(findByIp, LogFileEntity.class);
        Assertions.assertThat(fileEntities.size()).isEqualTo(0);
        Assertions.assertThat(ip).isEqualTo(EMPTY);

    }

}
