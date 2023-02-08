package com.cleaning.domain.cleaning_service.description;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.*;

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

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @NoArgsConstructor
    @Getter
    public static class CleaningDescriptionBuilder {

        private String standardCleaningDescription;
        private String deepCleaningDescription;
        private String postConstructionCleaningDescription;
        private String disinfectionCleaningDescription;

        public CleaningDescriptionBuilder withStandardCleaningDescription(String standardCleaningDescription) {

            this.standardCleaningDescription = standardCleaningDescription;
            return this;
        }

        public CleaningDescriptionBuilder withDeepCleaningDescription(String deepCleaningDescription) {

            this.deepCleaningDescription = deepCleaningDescription;
            return this;
        }

        public CleaningDescriptionBuilder withPostConstructionCleaningDescription(String postConstructionCleaningDescription) {

            this.postConstructionCleaningDescription = postConstructionCleaningDescription;
            return this;
        }

        public CleaningDescriptionBuilder withDisinfectionCleaningDescription(String disinfectionCleaningDescription) {

            this.disinfectionCleaningDescription = disinfectionCleaningDescription;
            return this;
        }

        public CleaningDescription build() {

            return new CleaningDescription(this);
        }
    }

    private CleaningDescription(CleaningDescriptionBuilder builder) {
        this.standardCleaningDescription = builder.getStandardCleaningDescription();
        this.deepCleaningDescription = builder.getDeepCleaningDescription();
        this.postConstructionCleaningDescription = builder.getPostConstructionCleaningDescription();
        this.disinfectionCleaningDescription = builder.getDisinfectionCleaningDescription();
    }
}
