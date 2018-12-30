package com.tb.bimo.exception;

import lombok.*;
import org.joda.time.DateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private final Long timeStamp = new DateTime().getMillis();
    private Integer status;
    private String error;
    private String message;
}
