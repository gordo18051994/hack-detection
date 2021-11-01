package com.fernandonieto.hackdetection.application.scheduler;

import com.fernandonieto.hackdetection.domain.port.input.HackerDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class LogEntryScheduler {

    @Value(value = "${example}")
    private String example;

    @Autowired
    private HackerDetector hackerDetector;

    @Scheduled(fixedRateString = "${fixedRate}")
    public void saveLog() {
        final String ip = hackerDetector.parseLine(example);
        log.info("Suspicious IP : {}", ip);

    }

}

