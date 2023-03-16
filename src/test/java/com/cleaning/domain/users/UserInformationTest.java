package com.cleaning.domain.users;

import com.cleaning.domain.users.data.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.assertj.core.api.Assertions.*;

public class UserInformationTest {

    @Test
    void should_create() {
        UserInformation userInformation = UserTestData.dummyUserInformation();

        assertThat(userInformation).isNotNull();
        assertThat(userInformation.getFullName()).isEqualTo("fullName");
        assertThat(userInformation.getAddress()).isEqualTo("address");
        assertThat(userInformation.getPhoneNumber()).isEqualTo("phoneNumber");
        assertThat(userInformation.getBirthDate()).isEqualTo(LocalDate.now());
    }
}
