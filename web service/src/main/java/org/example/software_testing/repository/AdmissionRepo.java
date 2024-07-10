package org.example.software_testing.repository;

import org.example.software_testing.entites.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepo extends JpaRepository<Admission,Integer> {
}
