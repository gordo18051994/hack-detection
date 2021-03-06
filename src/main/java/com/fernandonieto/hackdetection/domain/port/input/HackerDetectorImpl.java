package com.fernandonieto.hackdetection.domain.port.input;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.domain.model.LoginAction;
import com.fernandonieto.hackdetection.domain.port.output.LogFileRepository;
import com.fernandonieto.hackdetection.domain.validator.EntryValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HackerDetectorImpl implements HackerDetector {

    private static final String SEPARATOR = ",";
    private static final String EMPTY = "";

    @Autowired
    private LogFileRepository repository;
    @Autowired
    private EntryValidator validator;

    @SneakyThrows
    @Override
    public String parseLine(final String line) {
        final List<String> collect = Arrays.stream(line.split(SEPARATOR)).collect(Collectors.toList());
        final LogFile model = new LogFile(
                validator.ipValidate(collect.get(0)),
                validator.dateValidate(collect.get(1)),
                validator.actionValidate(collect.get(2)),
                collect.get(3));
        if (model.getAction().equals(LoginAction.SIGNIN_FAILURE)) {
            return manageLog(model);
        }
        return EMPTY;

    }

    private String manageLog(final LogFile model) {
        repository.deleteOlderLogins();
        final LogFile logFile = repository.saveLogin(model);
        return logFile.getSuspiciousIp();
    }
}
