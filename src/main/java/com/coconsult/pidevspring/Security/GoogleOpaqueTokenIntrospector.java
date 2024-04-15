package com.coconsult.pidevspring.Security;

import com.coconsult.pidevspring.Security.Oauth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor

public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private final WebClient userInfoClient;
    private static final Logger logger = LoggerFactory.getLogger(GoogleOpaqueTokenIntrospector.class);


    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo userInfo = userInfoClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", userInfo.sub());
        attributes.put("name", userInfo.name());
        logger.info(userInfo.name()+"----------------");

        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.name(), attributes, null);
    }
}
