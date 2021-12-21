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

@Entity
@ToString
@NoArgsConstructor
public class Address extends AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 255)
    private String city;

    @Getter @Setter
    @Column(nullable = false, length = 10)
    private String zip;

    @Getter @Setter
    @Column(nullable = false, length = 255)
    private String street;

}
