package com.api.busqueda.service;

import com.api.busqueda.model.ApiKey;
import com.api.busqueda.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import com.api.busqueda.security.AuthenticationMapper;

@Service
public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKeyValue = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKeyValue == null) {
            throw new InvalidApiKeyException("API Key no proporcionada");
        }

        // Buscar la API key en la base de datos
        ApiKey apiKey = apiKeyRepository.findByKeyAndIsActiveTrue(apiKeyValue)
                .orElseThrow(() -> new InvalidApiKeyException("API Key no válida o inactiva"));

        return new AuthenticationMapper(apiKey.getKey(), AuthorityUtils.NO_AUTHORITIES);
    }

    public static class InvalidApiKeyException extends RuntimeException {
        public InvalidApiKeyException(String message) {
            super(message);
        }
    }
}
