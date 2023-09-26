package com.firdose.ars.dto;

import java.time.LocalDateTime;

public class ErrorInfo {
    LocalDateTime time;
    String message;
    String uri;

    public ErrorInfo() {

    }

    public ErrorInfo(LocalDateTime time, String message, String uri) {
        super();
        this.time = time;
        this.message = message;
        this.uri = uri;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ErrorInfoDTO [time=" + time + ", message=" + message + ", uri=" + uri + "]";
    }

}
