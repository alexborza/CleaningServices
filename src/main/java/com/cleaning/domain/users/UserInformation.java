package com.cleaning.domain.users;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserInformation {
    private String fullName;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;
}

