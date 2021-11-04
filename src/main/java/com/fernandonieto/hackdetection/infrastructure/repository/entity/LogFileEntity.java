package com.fernandonieto.hackdetection.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LogFileEntity implements Serializable {

    private static final long serialVersionUID = 215146152372013507L;

    @Id
    private String ip;
    private LocalDateTime date;
    private String action;
    private String username;
    private Integer count;
}
