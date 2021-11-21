package com.example.restws.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String status_code;
    private String message;

    public ExceptionResponse(Date timestamp, String status_code, String message) {
        this.timestamp = timestamp;
        this.status_code = status_code;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
