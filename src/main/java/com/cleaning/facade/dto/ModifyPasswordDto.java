package com.cleaning.facade.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ModifyPasswordDto {
    private final String password;
    private final String newPassword;
}
