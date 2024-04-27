package com.coconsult.pidevspring.Services.TrainingSession;

public interface IEmailEventService {
    public void sendEventConfirmationEmail(Long userId, String eventName) ;
}
