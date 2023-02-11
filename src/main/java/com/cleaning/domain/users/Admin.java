package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Admin extends User {

    public static class Builder extends User.Builder<Admin.Builder> {

        @Override
        protected Admin.Builder self() {
            return this;
        }

        @Override
        public Admin build() {
            return new Admin(this);
        }
    }

    private Admin(Admin.Builder builder) {
        super(builder, Role.ADMIN);
    }
}
