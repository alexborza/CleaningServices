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
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return jpaRepository.findAllByRole(role);
    }
}
