package org.example.software_testing.repository;

import org.example.software_testing.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
}
