package com.cleaning.exposition.representation.request;

import lombok.*;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String email;
    private String role;
    private String password;
}
