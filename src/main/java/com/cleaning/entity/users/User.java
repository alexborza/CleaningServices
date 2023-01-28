package com.cleaning.entity.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
@Getter
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String email;
        private String password;

        @Embedded
        private UserInformation userInformation;

        @Enumerated(EnumType.STRING)
        private Role role;

        public User(String username, String email, String password, UserInformation userInformation, Role role) {
                this.username = username;
                this.email = email;
                this.password = password;
                this.userInformation = userInformation;
                this.role = role;
        }

        public User(String username, String password, Role role) {
                this.username = username;
                this.password = password;
                this.role = role;
        }
}
