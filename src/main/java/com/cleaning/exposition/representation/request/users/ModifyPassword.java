package com.cleaning.exposition.representation.request.users;

import lombok.*;

@Getter
@AllArgsConstructor
public class ModifyPassword {
    private final String password;
    private final String newPassword;
}
