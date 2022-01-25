package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostConstructionCleaningDetails extends CleaningDetails {

    @Enumerated(EnumType.STRING)
    private Property property;
    private int rooms;
}
