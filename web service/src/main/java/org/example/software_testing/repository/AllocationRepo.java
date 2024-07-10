package org.example.software_testing.repository;

import org.example.software_testing.entites.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepo extends JpaRepository<Allocation,Integer> {
}
