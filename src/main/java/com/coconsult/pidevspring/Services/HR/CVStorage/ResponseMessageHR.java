package com.coconsult.pidevspring.Services.HR.CVStorage;

public class ResponseMessageHR {
    private String message;

    public ResponseMessageHR(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
