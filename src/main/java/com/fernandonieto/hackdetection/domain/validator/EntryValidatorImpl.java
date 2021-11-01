package com.fernandonieto.hackdetection.domain.validator;

import com.fernandonieto.hackdetection.domain.exception.InvalidDataException;
import com.fernandonieto.hackdetection.domain.model.LogFile;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryValidatorImpl implements EntryValidator {

    private static final String IPV4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    private static final String INCORRECT_FORMAT_IP = "Incorrect format ip";
    private static final String INCORRECT_FORMAT_DATE = "Incorrect format date";
    private static final String INCORRECT_ACTION = "Incorrect action";

    @Override
    public String ipValidate(final String ip) {
        final Pattern pattern = Pattern.compile(IPV4_REGEX);
        final Matcher matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            throw new InvalidDataException(INCORRECT_FORMAT_IP);
        }
        return ip;
    }

    @Override
    public LocalDateTime dateValidate(final String date) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(date)), ZoneId.systemDefault());
        } catch (final NumberFormatException e) {
            throw new InvalidDataException(INCORRECT_FORMAT_DATE, e);
        }
    }

    @Override
    public LogFile.LoginAction actionValidate(final String action) {
        try {
            return LogFile.LoginAction.valueOf(action);
        } catch (final IllegalArgumentException e){
            throw new InvalidDataException(INCORRECT_ACTION, e);
        }
    }
}
