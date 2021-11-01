package com.fernandonieto.hackdetection.domain.validator;

import com.fernandonieto.hackdetection.domain.model.LogFile;

import java.time.LocalDateTime;

public interface EntryValidator {

    String ipValidate(String ip);

    LocalDateTime dateValidate(String date);

    LogFile.LoginAction actionValidate(String action);

}
