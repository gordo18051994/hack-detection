package com.fernandonieto.hackdetection.domain.port.input;

import com.fernandonieto.hackdetection.domain.model.LogFile;
import com.fernandonieto.hackdetection.domain.model.LoginAction;
import com.fernandonieto.hackdetection.domain.port.output.LogFileRepository;
import com.fernandonieto.hackdetection.domain.validator.EntryValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class HackerDetectorImplTest {

    private static final String IP = "80.238.9.179";
    private static final String SIGN_OK = "SIGNIN_SUCCESS";
    private static final String NAME = "Andy.Branning";
    private static final String ANY_TIME = "133612947";
    private static final String COMMA = ",";
    private static final String EMPTY = "";

    @InjectMocks
    private HackerDetectorImpl hackerDetector;
    @Mock
    private LogFileRepository repository;
    @Mock
    private EntryValidator validator;

    @Test
    public void is_a_login_success_should_return_empty_string() {
        //given
        LogFile logFile = new LogFile();
        when(validator.ipValidate(IP)).thenReturn(IP);
        when(validator.dateValidate(ANY_TIME)).thenReturn(LocalDateTime.now());
        when(validator.actionValidate(SIGN_OK)).thenReturn(LoginAction.SIGNIN_SUCCESS);
        when(validator.ipValidate(IP)).thenReturn(IP);
        //when
        final String ip = hackerDetector.parseLine(createSuccessLogline());
        //then
        Assertions.assertThat(ip).isEqualTo(EMPTY);

    }

    private String createSuccessLogline() {
        return String.join(COMMA, IP, ANY_TIME, SIGN_OK, NAME);
    }
}
