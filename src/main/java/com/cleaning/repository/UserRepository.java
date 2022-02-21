package com.cleaning.repository;

import com.cleaning.entity.*;
import com.cleaning.facade.vo.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("Select u from User u Where u.id = ?1")
    Optional<Client> findClientById(Long id);

    @Query("Select user.username as username, user.email as email from User user")
    List<UserCredentialVo> getUserCredentials();
}
