package com.cleaning.infrastructure.implementation;

import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return jpaRepository.saveAll(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByRole(Role role) {
        return jpaRepository.findByRole(role);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return jpaRepository.findAllByRole(role);
    }

    @Override
    public List<Long> findAllEmployeeIds() {
        return jpaRepository.findAllEmployeeIds();
    }

    @Override
    public List<UserMinimalView> findAllEmployeeMinimalViews() {

        return jpaRepository.findAllEmployeeMinimalViews();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public void updateEmail(Long userId, String email) {
        jpaRepository.updateEmail(userId, email);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        jpaRepository.updatePassword(userId, password);
    }

    @Override
    public void updateUserInformation(Long userId, UserInformation userInformation) {
        jpaRepository.updateUserInformation(userId, userInformation.getFullName(), userInformation.getAddress(), userInformation.getBirthDate(), userInformation.getPhoneNumber());
    }
}
