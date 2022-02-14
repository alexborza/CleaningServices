package com.cleaning.facade.dto.request;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String email;
    private Set<String> role;
    private String password;
}
