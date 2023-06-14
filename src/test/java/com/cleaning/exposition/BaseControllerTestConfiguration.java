package com.cleaning.exposition;

import com.cleaning.domain.users.*;
import com.cleaning.security.implementation.*;
import com.cleaning.security.jwt.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.web.servlet.*;

public class BaseControllerTestConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    // Unfortunately I need the PasswordEncoder to be Autowired not MockBean because it is used in order to encode the password of Admin..
    //and if I mock it, then it will generate a null password which is going to make the Admin object throw an error at validation point
    //due to NonEmpty constraint on password field
    private PasswordEncoder encoder;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    protected ObjectMapper objectMapper;
}
