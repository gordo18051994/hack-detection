package com.fernandonieto.hackdetection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogFile implements Serializable {

    private static final long serialVersionUID = -8694290682614828272L;
    private static final int MAX_LOGIN_FAIL = 5;

    public enum LoginAction {
        SIGNIN_SUCCESS,
        SIGNIN_FAILURE;
    }

    private String ip;
    private LocalDateTime date;
    private LoginAction action;
    private String username;
    private Integer count;

    public LogFile(final String ip, final LocalDateTime date, final LoginAction action, final String username) {
        this.ip = ip;
        this.date = date;
        this.action = action;
        this.username = username;
        this.count = 1;
    }
    
    public String getSuspiciousIp() {
        return this.count >= LogFile.MAX_LOGIN_FAIL ? this.ip : "";
    }
}
