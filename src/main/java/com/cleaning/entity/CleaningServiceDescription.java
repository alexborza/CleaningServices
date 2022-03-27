package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CleaningServiceDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String standardCleaningDescription;

    @Lob
    private String deepCleaningDescription;

    @Lob
    private String postConstructionCleaningDescription;

    @Lob
    private String disinfectionCleaningDescription;
}
