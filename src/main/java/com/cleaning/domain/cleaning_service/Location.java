package com.cleaning.domain.cleaning_service;

import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Location {
    private String county;
    private String city;
    private String address;
}
