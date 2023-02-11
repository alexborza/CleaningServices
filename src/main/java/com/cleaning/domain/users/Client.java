package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Client extends User {

    public static class Builder extends User.Builder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Client build() {
            return new Client(this);
        }
    }

    private Client(Builder builder) {
        super(builder, Role.USER);
    }
}
