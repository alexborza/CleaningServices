package com.cleaning.exposition.representation.response.users;

import com.cleaning.domain.users.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class UserMinimalRepresentation {
    private Long id;
    private String fullName;

    public static UserMinimalRepresentation fromDomain(UserMinimalView userMinimalView) {
        return new UserMinimalRepresentation(
                userMinimalView.getId(),
                userMinimalView.getFullName()
        );
    }
}
