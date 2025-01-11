package com.lambda_complex.generala.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "player")
@EntityListeners(AuditingEntityListener.class)
//@ToString
public class Player {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Column(name = "is_registered")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isRegistered;
    @Column(name = "registration_date")
    private Instant registrationDate;

    // Timestamps
    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @LastModifiedDate
    @Column(name = "modification_date")
    private Instant modificationDate;

    // Relations
    @OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Match> matchesOwned;
    @OneToMany(mappedBy = "player", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<MatchPlayer> matchesPlayedIn;
    @OneToMany(mappedBy = "player", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Play> plays;
}
