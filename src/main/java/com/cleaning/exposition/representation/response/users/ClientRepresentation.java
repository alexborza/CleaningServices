package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import static com.cleaning.domain.users.Role.*;

@Getter
@JsonTypeName("client")
public class ClientRepresentation extends UserRepresentation {

    public ClientRepresentation(Long id, String username, String email, String password, UserInformationRepresentation userInformation) {
        super(id, username, email, password, userInformation);
    }

    @Override
    public User toDomain() {
        return new Client.Builder()
                .withUsername(getUsername())
                .withEmail(getEmail())
                .withPassword(getPassword())
                .withUserInformation(getUserInformation().toDomain())
                .build();
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
