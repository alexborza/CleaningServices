package com.cleaning.exposition.representation.request;

import lombok.*;

@Getter
@AllArgsConstructor
public class ModifyPassword {
    private final String password;
    private final String newPassword;
}
