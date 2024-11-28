package org.oceanmap.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    private int iconCode;
    private String iconPhrase;
    private String precipitationType;
    private String precipitationIntensity;
    private String shortPhrase;
    private String longPhrase;
    private int rainProbability;
    private Wind wind;
    private WindGust windGust;
    private double hoursOfRain;
    private int cloudCover;
}