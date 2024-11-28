package org.oceanmap.dto.weather;

import lombok.Data;

@Data
public class WindGust {
    private Direction direction;
    private Speed speed;
}
