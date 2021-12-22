package org.evidence.computer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@ToString
@NoArgsConstructor
public class Address extends AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @NotBlank(message = "City cannot be empty")
    @Size(min = 1, max = 255, message = "City must be between 1 and 255 characters")
    @Column(nullable = false, length = 255)
    private String city;

    @Getter @Setter
    @NotBlank(message = "Zip code cannot be empty")
    @Size(min = 1, max = 10, message = "Zip must be between 1 and 10 characters")
    @Column(nullable = false, length = 10)
    private String zip;

    @Getter @Setter
    @NotBlank(message = "Street cannot be empty")
    @Size(min = 1, max = 255, message = "Street must be between 1 and 255 characters")
    @Column(nullable = false, length = 255)
    private String street;

}
