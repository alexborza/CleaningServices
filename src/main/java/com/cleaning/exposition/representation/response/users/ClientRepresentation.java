package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@Getter
public class ClientRepresentation extends UserRepresentation {

    public ClientRepresentation(Long id, String username, String email, UserInformationRepresentation userInformation) {
        super(id, username, email, userInformation);
    }

    public static ClientRepresentation fromDomain(Client client) {
        return new ClientRepresentation(
                client.getId(),
                client.getUsername(),
                client.getEmail(),
                UserInformationRepresentation.fromDomain(client.getUserInformation())
        );
    }
}
