package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<CleaningService> cleaningServices;

    public Client(String username, String email, String password) {
        super(username, email, password);
    }

    public void addCleaningService(CleaningService cleaningService){
        this.cleaningServices.add(cleaningService);
    }
}
