package org.evidence.computer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
class Computer extends AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Getter @Setter
    @NotBlank(message = "Domain cannot be empty")
    @Size(min = 1, max = 100, message = "Domain must be between 1 and 100 characters")
    @Column(nullable = false, length = 100)
    private String domain;

    @Getter @Setter
    @NotBlank(message = "Vendor cannot be empty")
    @Size(min = 1, max = 255, message = "Vendor must be between 1 and 255 characters")
    @Column(nullable = false, length = 255)
    private String vendor;

    @Getter @Setter
    @NotNull(message = "Purchase date must be set")
    @Column(nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "locality_id", nullable = false)
    @Getter @Setter
    private Address locality;
}
