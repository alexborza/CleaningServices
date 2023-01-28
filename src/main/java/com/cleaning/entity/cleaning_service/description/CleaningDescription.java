package com.cleaning.entity.cleaning_service.description;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cleaning_description")
@NoArgsConstructor
@Getter
public class CleaningDescription {
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

    public CleaningDescription(String standardCleaningDescription, String deepCleaningDescription, String postConstructionCleaningDescription, String disinfectionCleaningDescription) {
        this.standardCleaningDescription = standardCleaningDescription;
        this.deepCleaningDescription = deepCleaningDescription;
        this.postConstructionCleaningDescription = postConstructionCleaningDescription;
        this.disinfectionCleaningDescription = disinfectionCleaningDescription;
    }
}
