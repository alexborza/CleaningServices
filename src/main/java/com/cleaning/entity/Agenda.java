package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Employee_Agenda")
@Getter
@Setter
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<>();

    @OneToOne(mappedBy = "agenda")
    private Employee employee;
}
