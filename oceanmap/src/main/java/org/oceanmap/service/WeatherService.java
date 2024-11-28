package org.oceanmap.service;

import org.oceanmap.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    private final RestClient restClient;

    @Value("${atlas.api.key}")
    private String apiKey;

    private static final String WEATHER_BASE_URL = "https://atlas.microsoft.com/weather/forecast/daily/json";
    private static final double API_VERSION = 1.1;
    private static final String LANGUAGE = "pt-BR";

    public WeatherService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(WEATHER_BASE_URL)
                .build();
    }

    public WeatherDTO getWeatherByCoordinates(String coordinates) {
        try {
            String fullUrl = UriComponentsBuilder.fromHttpUrl(WEATHER_BASE_URL)
                    .queryParam("api-version", API_VERSION)
                    .queryParam("query", coordinates)
                    .queryParam("language", LANGUAGE)
                    .queryParam("units", 17)
                    .queryParam("subscription-key", apiKey)
                    .build()
                    .toUriString();


            return restClient.get()
                    .uri(fullUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(WeatherDTO.class);
        } catch (Exception e) {
            throw new WeatherException("Erro ao buscar clima por coordenadas: " + e.getMessage());
        }
    }

    public static class WeatherException extends RuntimeException {
        public WeatherException(String message) {
            super(message);
        }
    }
}
