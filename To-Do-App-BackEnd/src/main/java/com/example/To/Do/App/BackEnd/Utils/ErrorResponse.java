package com.example.To.Do.App.BackEnd.Utils;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private Integer statusNumber;
    private String message;
    private Date timestamp;
}
