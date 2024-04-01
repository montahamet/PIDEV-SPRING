package com.coconsult.pidevspring.filesupload.message;

public class ResponseMessagedoc {
    private String message;

    public ResponseMessagedoc(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
