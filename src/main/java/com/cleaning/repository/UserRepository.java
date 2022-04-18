package com.cleaning.repository;

import com.cleaning.entity.*;
import com.cleaning.facade.vo.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository<E extends User> extends JpaRepository<E, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(ERole role);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("Select user.username as username, user.email as email from User user")
    List<UserCredentialVo> getUserCredentials();
}
