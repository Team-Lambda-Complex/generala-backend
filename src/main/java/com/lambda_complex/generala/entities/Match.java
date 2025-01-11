package com.lambda_complex.generala.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match")
@EntityListeners(AuditingEntityListener.class)
public class Match {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_active")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isActive;

    // Timestamps
    @CreatedDate
    @Column(name = "creation_date")
    private Instant creationDate;
    @LastModifiedDate
    @Column(name = "modification_date")
    private Instant modificationDate;

    // Relations
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "owner_id", nullable = false)
    private Player owner;
    @OneToMany(mappedBy = "match", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<MatchPlayer> players;
    @OneToMany(mappedBy = "match", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Play> plays;
}
