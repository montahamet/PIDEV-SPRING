package com.coconsult.pidevspring.Security.Oauth;

import com.coconsult.pidevspring.DAO.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TokenUserDetailsDto {
    private String token;
    private User userDetails;

    public TokenUserDetailsDto(String token, User userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    // Getters and setters
}