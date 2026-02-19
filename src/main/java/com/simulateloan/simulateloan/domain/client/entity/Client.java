package com.simulateloan.simulateloan.domain.client.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Client {

    private final UUID id;
    private String name;
    private BigDecimal grossSalary;

    public Client(UUID id, String name, BigDecimal grossSalary) {
        this.id = id;
        this.name = name;
        this.grossSalary = grossSalary;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

}
