package com.cleaning.domain.users;

import java.util.*;

public interface Users {

    User save(User user);

    List<User> findAllByRole(Role role);
}
