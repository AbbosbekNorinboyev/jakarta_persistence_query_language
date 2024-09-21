package com.example.lesson8.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country_name", column = @Column(name = "address_country_name")),
            @AttributeOverride(name = "city_name", column = @Column(name = "address_city_name")),
            @AttributeOverride(name = "zip_code", column = @Column(name = "address_zip_code"))
    })
    private Address address;
}
