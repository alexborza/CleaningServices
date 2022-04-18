package com.cleaning.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String username;
        private String email;
        private String password;

        @Embedded
        private UserInformation userInformation;

        @Enumerated(EnumType.STRING)
        private ERole role;

        public User(String username, String email, String password) {
                this.username = username;
                this.email = email;
                this.password = password;
        }

        public User(String username, String password){
                this.username = username;
                this.password = password;
        }
}
