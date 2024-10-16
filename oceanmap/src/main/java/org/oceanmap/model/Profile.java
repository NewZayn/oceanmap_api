package org.oceanmap.model;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "profile")
public class Profile {
    private User user;
    private String bio;
    private String address;
    private String city;
    private String state;
}