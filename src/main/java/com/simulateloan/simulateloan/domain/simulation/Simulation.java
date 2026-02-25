package com.simulateloan.simulateloan.domain.simulation;

import java.math.BigDecimal;
import java.util.UUID;

public class Simulation {
     UUID id;
     BigDecimal netSalary;
     public String track;
     public BigDecimal totalLimit;
     public Integer installments;
     public BigDecimal installmentsValue;
     public BigDecimal percentageOfSalary;

    public Simulation (UUID id, BigDecimal netSalary, String track, BigDecimal totalLimit, Integer installments, BigDecimal installmentsValue, BigDecimal percentageOfSalary){
        this.netSalary = netSalary;
        this.track = track;
        this.totalLimit = totalLimit;
        this.installments = installments;
        this.installmentsValue = installmentsValue;
        this.percentageOfSalary = percentageOfSalary;
    }
}
