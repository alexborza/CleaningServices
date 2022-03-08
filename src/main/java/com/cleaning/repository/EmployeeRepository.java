package com.cleaning.repository;

import com.cleaning.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface EmployeeRepository extends UserRepository<Employee> {

    @Query("Select e.agenda from Employee e")
     List<Agenda> getEmployeesAgendaForDate(String cleaningDate);
}
