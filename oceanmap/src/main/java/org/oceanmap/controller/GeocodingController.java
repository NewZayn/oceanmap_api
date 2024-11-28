package org.oceanmap.controller;


import org.oceanmap.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class GeocodingController {
    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/geocode")
    public ResponseEntity<?> geocode(@RequestParam String address){
        try {
            return ResponseEntity.ok(geocodingService.geocodeAddress(address));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
