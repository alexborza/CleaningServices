package com.cleaning.infrastructure;

import com.cleaning.domain.users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :userId")
    void updateEmail(@Param("userId") Long userId, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);

    //not the most optimal query...in verbosity I mean
    @Transactional
    @Modifying
    @Query("UPDATE User u SET " +
            "u.userInformation.fullName = :fullName, " +
            "u.userInformation.address = :address, " +
            "u.userInformation.phoneNumber = :phoneNumber, " +
            "u.userInformation.birthDate = :birthDate " +
            "WHERE u.id = :userId")
    void updateUserInformation(
            @Param("userId") Long userId,
            @Param("fullName") String fullName,
            @Param("address") String address,
            @Param("birthDate") String birthDate,
            @Param("phoneNumber") String phoneNumber);

    List<User> findAllByRole(Role role);

    @Query("Select u.id From User u where u.role = 'EMPLOYEE'")
    List<Long> findAllEmployeeIds();

    @Query("Select u.id as id, u.userInformation.fullName as fullName From User u where u.role = 'EMPLOYEE'")
    List<UserMinimalView> findAllEmployeeMinimalViews();
}
