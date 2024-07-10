package org.example.software_testing.repository;

import org.example.software_testing.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
