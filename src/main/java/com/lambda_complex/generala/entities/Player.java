package com.lambda_complex.generala.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "user")
//@ToString
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Column(name = "is_registered")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isRegistered;
    @CreatedDate
    @Column(name = "registration_date")
    private Instant registrationDate;

    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @LastModifiedDate
    @Column(name = "modification_date")
    private Instant modificationDate;

    // Relations
    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST })
    private Set<Match> matches;
}
