package org.oceanmap.service;



import org.oceanmap.dto.GeocodingDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class GeocodingService {

    private static final String BASE_URL = "https://atlas.microsoft.com/geocode";
    private static final String VIEW = "BR";
    private static final String API_VERSION = "2023-06-01";
    private static final String LANGUAGE = "pt-BR";

    private final RestClient restClient;

    @Value("${atlas.api.key}")
    private String apiKey;

    public GeocodingService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(BASE_URL)
                .build();
    }

    public GeocodingDTO geocodeAddress(String query) {
        try{

            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String fullUrl = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("subscription-key", apiKey)
                    .queryParam("api-version", API_VERSION)
                    .queryParam("query", encodedQuery)
                    .queryParam("view", VIEW)
                    .queryParam("language", LANGUAGE)
                    .build()
                    .toUriString();

            return restClient.get()
                    .uri(fullUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(GeocodingDTO.class);
    } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar coordenadas: " + e.getMessage());
        }
}
}