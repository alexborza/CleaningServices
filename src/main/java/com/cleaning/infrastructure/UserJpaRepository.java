package com.cleaning.infrastructure;

import com.cleaning.domain.users.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);

    List<User> findAllByRole(Role role);
}
