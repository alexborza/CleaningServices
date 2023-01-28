package com.cleaning.entity.users;

import com.cleaning.entity.cleaning_service.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private List<CleaningService> cleaningServices;

    public Client(String username, String email, String password, UserInformation userInformation, List<CleaningService> cleaningServices) {
        super(username, email, password, userInformation, Role.USER);
        this.cleaningServices = cleaningServices;
    }

    public void addCleaningService(CleaningService cleaningService){
        this.cleaningServices.add(cleaningService);
    }
}
