package com.lambda_complex.generala.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerDto {
    private Long id;
    private String name;
    private String email;
    private Boolean isRegistered;
    private Instant registrationDate;
    private Instant creationDate;
    private Instant modificationDate;
}
