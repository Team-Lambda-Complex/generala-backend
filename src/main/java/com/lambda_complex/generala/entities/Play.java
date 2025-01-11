package com.lambda_complex.generala.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "play")
public class Play {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_served")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isServed;
    private int points;

    // Timestamps
    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @LastModifiedDate
    @Column(name = "modification_date")
    private Instant modificationDate;

    // Relations
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PlayType type;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
