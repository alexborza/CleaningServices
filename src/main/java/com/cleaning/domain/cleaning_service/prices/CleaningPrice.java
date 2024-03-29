package com.cleaning.domain.cleaning_service.prices;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
public class CleaningPrice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @NotNull
    private StandardCleaningPrice standardCleaningPrice;

    @Embedded
    @NotNull
    private DeepCleaningPrice deepCleaningPrice;

    @Embedded
    @NotNull
    private PostConstructionCleaningPrice postConstructionCleaningPrice;

    @Embedded
    @NotNull
    private DisinfectionCleaningPrice disinfectionCleaningPrice;

    @NotNull
    private Double paidParkingSpotPrice;

    @NotNull
    private Double pickUpKeysPrice;

    @NoArgsConstructor
    @Getter
    public static class CleaningPriceBuilder {

        private StandardCleaningPrice standardCleaningPrice;
        private DeepCleaningPrice deepCleaningPrice;
        private PostConstructionCleaningPrice postConstructionCleaningPrice;
        private DisinfectionCleaningPrice disinfectionCleaningPrice;
        private Double paidParkingSpotPrice;
        private Double pickUpKeysPrice;

        public CleaningPriceBuilder withStandardCleaningPrice(StandardCleaningPrice standardCleaningPrice) {

            this.standardCleaningPrice = standardCleaningPrice;
            return this;
        }

        public CleaningPriceBuilder withDeepCleaningPrice(DeepCleaningPrice deepCleaningPrice) {

            this.deepCleaningPrice = deepCleaningPrice;
            return this;
        }

        public CleaningPriceBuilder withPostConstructionCleaningPrice(PostConstructionCleaningPrice postConstructionCleaningPrice) {

            this.postConstructionCleaningPrice = postConstructionCleaningPrice;
            return this;
        }

        public CleaningPriceBuilder withDisinfectionCleaningPrice(DisinfectionCleaningPrice disinfectionCleaningPrice) {

            this.disinfectionCleaningPrice = disinfectionCleaningPrice;
            return this;
        }

        public CleaningPriceBuilder withPaidParkingSpotPrice(Double paidParkingSpotPrice) {

            this.paidParkingSpotPrice = paidParkingSpotPrice;
            return this;
        }

        public CleaningPriceBuilder withPickUpKeysPrice(Double pickUpKeysPrice) {

            this.pickUpKeysPrice = pickUpKeysPrice;
            return this;
        }

        public CleaningPrice build() {

            return new CleaningPrice(this);
        }

    }

    private CleaningPrice(CleaningPriceBuilder builder) {
        this.standardCleaningPrice = builder.getStandardCleaningPrice();
        this.deepCleaningPrice = builder.getDeepCleaningPrice();
        this.postConstructionCleaningPrice = builder.getPostConstructionCleaningPrice();
        this.disinfectionCleaningPrice = builder.getDisinfectionCleaningPrice();
        this.paidParkingSpotPrice = builder.getPaidParkingSpotPrice();
        this.pickUpKeysPrice = builder.getPickUpKeysPrice();
        validate(this);
    }
}
