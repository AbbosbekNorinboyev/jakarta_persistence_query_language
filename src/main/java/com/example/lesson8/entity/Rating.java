package com.example.lesson8.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Converts({
//        @Convert(
//                attributeName = "rate",
//                converter = RateConvertor.class
//        ),
//        @Convert(
//                attributeName = "rate",
//                converter = LocalDateTimeConverter.class
//        )
//})
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Convert(converter = RateConvertor.class)
    private Integer rate;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime created_at;
}

@Converter(autoApply = true)
class RateConvertor implements AttributeConverter<Integer, String> {
    @Override
    public String convertToDatabaseColumn(Integer integerAttribute) {
        return switch (integerAttribute) {
            case 1 -> "*";
            case 2 -> "**";
            case 3 -> "***";
            case 4 -> "****";
            case 5 -> "*****";
            default -> "*****";
        };
    }

    @Override
    public Integer convertToEntityAttribute(String s) {
        return s.length();
    }
}
@Converter(autoApply = true)
class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
