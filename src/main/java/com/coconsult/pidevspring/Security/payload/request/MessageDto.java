package com.coconsult.pidevspring.Security.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private String msg;
    private String t_stamp;
    private Long senderId;
    private Long receiverId;
    private String senderEmail;
}