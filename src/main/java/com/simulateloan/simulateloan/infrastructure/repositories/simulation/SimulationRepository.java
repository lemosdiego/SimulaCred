package com.simulateloan.simulateloan.infrastructure.repositories.simulation;

import com.simulateloan.simulateloan.infrastructure.entity.simulation.SimulationJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationJpa, UUID> { }
