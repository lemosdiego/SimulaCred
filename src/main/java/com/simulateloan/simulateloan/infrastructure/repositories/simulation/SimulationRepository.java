package com.simulateloan.simulateloan.infrastructure.repositories;

import com.simulateloan.simulateloan.infrastructure.entity.SimulationJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationJpa, UUID> { }
