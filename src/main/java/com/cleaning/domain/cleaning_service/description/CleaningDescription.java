package com.cleaning.domain.cleaning_service.description;

import com.cleaning.domain.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.*;

@Entity
@Table(name = "cleaning_description")
@NoArgsConstructor
@Getter
public class CleaningDescription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @NotBlank
    @NotNull
    private String standardCleaningDescription;


    @Lob
    @NotBlank
    @NotNull
    private String deepCleaningDescription;

    @Lob
    @NotBlank
    @NotNull
    private String postConstructionCleaningDescription;

    @Lob
    @NotBlank
    @NotNull
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
        validate(this);
    }
}
