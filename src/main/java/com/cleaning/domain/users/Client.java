package com.cleaning.domain.users;

import com.cleaning.domain.cleaning_service.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<CleaningService> cleaningServices;

    public Client(String username, String email, String password, List<CleaningService> cleaningServices) {
        super(username, email, password, null, Role.USER);
        this.cleaningServices = cleaningServices;
    }

    public void addCleaningService(CleaningService cleaningService){
        this.cleaningServices.add(cleaningService);
    }
}
