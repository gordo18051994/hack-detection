package com.fernandonieto.hackdetection.domain.validator;

import com.fernandonieto.hackdetection.domain.model.LoginAction;

import java.time.LocalDateTime;

public interface EntryValidator {

    String ipValidate(String ip);

    LocalDateTime dateValidate(String date);

    LoginAction actionValidate(String action);

}
