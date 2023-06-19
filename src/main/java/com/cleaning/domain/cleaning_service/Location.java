package com.cleaning.domain.cleaning_service;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Embeddable
@NoArgsConstructor
@Getter
public class Location extends BaseEntity {

    @NotBlank
    private String county;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    public Location(String county, String city, String address) {
        this.county = county;
        this.city = city;
        this.address = address;
        validate(this);
    }
}
