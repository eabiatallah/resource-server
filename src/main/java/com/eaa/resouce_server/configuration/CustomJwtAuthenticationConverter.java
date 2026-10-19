package com.eaa.resouce_server.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, CustomAuthentication> {

    // Maps the "scope" claim to SCOPE_* authorities
    private final JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public CustomAuthentication convert(Jwt source) {
        Collection<GrantedAuthority> authorities = authoritiesConverter.convert(source);

        // Custom claim; null when absent
        String priority = source.getClaimAsString("priority");

        return new CustomAuthentication(source, authorities, priority);
    }
}
