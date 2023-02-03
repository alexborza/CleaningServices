package com.cleaning.domain.cleaning_service.prices;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CleaningPrices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private StandardCleaningPrices standardCleaningPrices;

    @Embedded
    private DeepCleaningPrices deepCleaningPrices;

    @Embedded
    private PostConstructionCleaningPrices postConstructionCleaningPrices;

    @Embedded
    private DisinfectionCleaningPrices disinfectionCleaningPrices;

    private Double paidParkingSpotPrice;
    private Double pickUpKeysPrice;

    @NoArgsConstructor
    @Getter
    public static class CleaningPricesBuilder {

        private StandardCleaningPrices standardCleaningPrices;
        private DeepCleaningPrices deepCleaningPrices;
        private PostConstructionCleaningPrices postConstructionCleaningPrices;
        private DisinfectionCleaningPrices disinfectionCleaningPrices;
        private Double paidParkingSpotPrice;
        private Double pickUpKeysPrice;

        public CleaningPricesBuilder withStandardCleaningPrices(StandardCleaningPrices standardCleaningPrices) {

            this.standardCleaningPrices = standardCleaningPrices;
            return this;
        }

        public CleaningPricesBuilder withDeepCleaningPrices(DeepCleaningPrices deepCleaningPrices) {

            this.deepCleaningPrices = deepCleaningPrices;
            return this;
        }

        public CleaningPricesBuilder withPostConstructionCleaningPrices(PostConstructionCleaningPrices postConstructionCleaningPrices) {

            this.postConstructionCleaningPrices = postConstructionCleaningPrices;
            return this;
        }

        public CleaningPricesBuilder withDisinfectionCleaningPrices(DisinfectionCleaningPrices disinfectionCleaningPrices) {

            this.disinfectionCleaningPrices = disinfectionCleaningPrices;
            return this;
        }

        public CleaningPricesBuilder withPaidParkingSpotPrice(Double paidParkingSpotPrice) {

            this.paidParkingSpotPrice = paidParkingSpotPrice;
            return this;
        }

        public CleaningPricesBuilder withPickUpKeysPrice(Double pickUpKeysPrice) {

            this.pickUpKeysPrice = pickUpKeysPrice;
            return this;
        }

        public CleaningPrices build() {

            return new CleaningPrices(this);
        }

    }

    private CleaningPrices(CleaningPricesBuilder builder) {
        this.standardCleaningPrices = builder.getStandardCleaningPrices();
        this.deepCleaningPrices = builder.getDeepCleaningPrices();
        this.postConstructionCleaningPrices = builder.getPostConstructionCleaningPrices();
        this.disinfectionCleaningPrices = builder.getDisinfectionCleaningPrices();
        this.paidParkingSpotPrice = builder.getPaidParkingSpotPrice();
        this.pickUpKeysPrice = builder.getPickUpKeysPrice();
    }
}
