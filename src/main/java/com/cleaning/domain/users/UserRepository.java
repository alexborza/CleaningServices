package com.cleaning.domain.users;

import java.util.*;

public interface UserRepository {

    User save(User user);

    List<User> saveAll(List<User> users);

    Optional<User> findById(Long id);

    List<User> findAllByRole(Role role);

    List<Long> findAllEmployeeIds();

    boolean existsById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void updateEmail(Long userId, String email);

    void updatePassword(Long userId, String password);

    void updateUserInformation(Long userId, UserInformation userInformation);

}
