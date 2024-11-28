package org.oceanmap.controller;

import org.oceanmap.dto.GeocodingDTO;
import org.oceanmap.dto.WeatherDTO;
import org.oceanmap.service.GeocodingService;
import org.oceanmap.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class WeatherController {

    private final WeatherService weatherService;
    private final GeocodingService geocodingService;

    public WeatherController(WeatherService weatherService, GeocodingService geocodingService) {
        this.weatherService = weatherService;
        this.geocodingService = geocodingService;
    }


    @GetMapping("/weather")
    public ResponseEntity<WeatherDTO> getWeatherByCity(@RequestParam String address) {
        GeocodingDTO geocodingDTO = geocodingService.geocodeAddress(address);

        double latitude = geocodingDTO.getFeatures().getFirst().getGeometry().getCoordinates()[1];
        double longitude = geocodingDTO.getFeatures().getFirst().getGeometry().getCoordinates()[0];

        String coordinates = latitude + "," + longitude;

        WeatherDTO weather = weatherService.getWeatherByCoordinates(
                coordinates
        );

        return ResponseEntity.ok(weather);
    }
}