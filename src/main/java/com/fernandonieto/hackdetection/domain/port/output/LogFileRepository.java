package com.fernandonieto.hackdetection.domain.port.output;

import com.fernandonieto.hackdetection.domain.model.LogFile;

public interface LogFileRepository {

    LogFile saveLogin(LogFile model);

    void deleteOlderLogins();

}
