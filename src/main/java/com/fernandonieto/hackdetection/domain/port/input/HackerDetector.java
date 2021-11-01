package com.fernandonieto.hackdetection.domain.port.input;

public interface HackerDetector {

    String parseLine(String logFile);
}
