package com.example.SpringPractica;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spotify.api")
public class SpotifyApiProperties {
    private String baseUrl;
    private String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String buildUrl(String endpoint) {
        return baseUrl + endpoint;
    }
}
