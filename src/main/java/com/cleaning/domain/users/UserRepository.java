package com.cleaning.domain.users;

import java.util.*;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAllByRole(Role role);
}
