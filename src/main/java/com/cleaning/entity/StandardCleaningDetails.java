package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StandardCleaningDetails extends CleaningDetails {
    private int bedrooms;
    private int bathrooms;
    private int kitchens;
}
