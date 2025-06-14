package com.claudsaints.scrumflow.controllers.exceptions;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StandardError implements Serializable {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
}
