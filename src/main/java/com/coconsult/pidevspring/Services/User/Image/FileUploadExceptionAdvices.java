package com.coconsult.pidevspring.Services.User.Image;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
<<<<<<<< HEAD:src/main/java/com/coconsult/pidevspring/Services/HR/CVStorage/FileUploadExceptionAdviceHR.java
public class FileUploadExceptionAdviceHR extends ResponseEntityExceptionHandler{
========

@ControllerAdvice
public class FileUploadExceptionAdvices extends ResponseEntityExceptionHandler {

>>>>>>>> 340bb1611de4d28d73c923a57941f8b1cd8d1183:src/main/java/com/coconsult/pidevspring/Services/User/Image/FileUploadExceptionAdvices.java
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessageHR> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageHR("File too large!"));
    }
}