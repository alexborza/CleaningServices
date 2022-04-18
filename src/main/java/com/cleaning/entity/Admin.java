package com.cleaning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    public Admin(String username, String password){
        super(username, password);
    }
}
