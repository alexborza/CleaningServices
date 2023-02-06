package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Client extends User {

    public Client(String username, String email, String password, UserInformation userInformation) {
        super(username, email, password, userInformation, Role.USER);
    }
}
