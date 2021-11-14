package org.evidence.computer;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@NoArgsConstructor
class Computer extends AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Getter @Setter
    @Column(nullable = false, length = 100)
    private String domain;

    @Getter @Setter
    @Column(nullable = false, length = 255)
    private String vendor;

    @Getter @Setter
    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn(name = "locality_id", nullable = false)
    @Getter @Setter
    private Address locality;
}
