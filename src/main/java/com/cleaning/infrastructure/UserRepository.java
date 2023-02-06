package com.cleaning.infrastructure;

import com.cleaning.domain.users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
//
//    @Query("Select user.username as username, user.email as email from User user")
//    List<UserCredentialVo> getUserCredentials();
}
