package com.springboot.blog.springbootblogrestapi.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String uri;

    public ErrorDetails(Date timeStamp, String message, String uri) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.uri = uri;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}
