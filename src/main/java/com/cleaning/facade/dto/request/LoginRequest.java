package com.cleaning.facade.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
