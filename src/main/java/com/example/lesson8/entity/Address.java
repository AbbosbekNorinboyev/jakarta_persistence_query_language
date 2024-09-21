package com.example.lesson8.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    private String country_name;
    private String city_name;
    private String zip_code;
}
