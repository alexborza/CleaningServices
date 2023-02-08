package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@JsonTypeName("client")
public class ClientRepresentation extends UserRepresentation {

    public ClientRepresentation(Long id, String username, String email, String password, UserInformationRepresentation userInformation) {
        super(id, username, email, password, userInformation);
    }

    @Override
    public User toDomain() {
        return new Client(
                getUsername(),
                getEmail(),
                getPassword(),
                getUserInformation().toDomain()
        );
    }

    public static ClientRepresentation mapFromDomain(Client client) {
        return new ClientRepresentation(
                client.getId(),
                client.getUsername(),
                client.getEmail(),
                client.getPassword(),
                UserInformationRepresentation.fromDomain(client.getUserInformation())
        );
    }
}
