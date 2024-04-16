package com.coconsult.pidevspring.Security.Oauth;

import com.coconsult.pidevspring.Security.Services.UserDetailsImpl;

public record TokenDto(String token, UserDetailsImpl user) {
}
