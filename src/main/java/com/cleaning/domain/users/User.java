package com.cleaning.domain.users;

import com.cleaning.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
@Getter
public abstract class User extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String username;

        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @Embedded
        private UserInformation userInformation;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Role role;

        @NoArgsConstructor
        @Getter
        public abstract static class Builder<T extends Builder<T>> {
                private Long id;
                private String username;
                private String email;
                private String password;
                private UserInformation userInformation;

                public T withId(Long id){
                        this.id = id;
                        return self();
                }

                public T withUsername(String username){
                        this.username = username;
                        return self();
                }

                public T withEmail(String email) {
                        this.email = email;
                        return self();
                }

                public T withPassword(String password) {
                        this.password = password;
                        return self();
                }

                public T withUserInformation(UserInformation userInformation) {
                        this.userInformation = userInformation;
                        return self();
                }

                protected abstract T self();

                public abstract User build();
        }

        protected User(Builder<?> builder, Role role) {
                this.id = builder.getId();
                this.username = builder.getUsername();
                this.email = builder.getEmail();
                this.password = builder.getPassword();
                this.userInformation = builder.getUserInformation();
                this.role = role;
        }
}
