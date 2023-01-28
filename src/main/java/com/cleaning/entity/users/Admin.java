package com.cleaning.entity.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);
    }
}
