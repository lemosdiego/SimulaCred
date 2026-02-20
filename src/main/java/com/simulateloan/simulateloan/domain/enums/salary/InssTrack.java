package com.simulateloan.simulateloan.domain.enums.salary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum InssTrack {
    Track_1(BigDecimal.ZERO, new BigDecimal("1621"), new BigDecimal("0.075"), BigDecimal.ZERO),
    Track_2(new BigDecimal("1621.01"), new BigDecimal("2902.84"), new BigDecimal("0.09"), new BigDecimal("24.32")),
    Track_3(new BigDecimal("2902.85"), new BigDecimal("4354.27"), new BigDecimal("0.12"), new BigDecimal("92.88")),
    Track_4(new BigDecimal("4354.28"), new BigDecimal("8475.55"), new BigDecimal("0.14"), new BigDecimal("198.50"));

    public final BigDecimal minimum;
    public final BigDecimal maximum;
    public final BigDecimal aliquot;
    public final BigDecimal deductInstallment;

    InssTrack(BigDecimal minimum, BigDecimal maximum, BigDecimal aliquot, BigDecimal deductInstallment){
        this.minimum = minimum;
        this.maximum = maximum;
        this.aliquot = aliquot;
        this.deductInstallment = deductInstallment;
    }

    public static InssTrack trackFor(BigDecimal salary){
        return Arrays.stream(values())
                .filter(f -> salary.compareTo(f.minimum) > 0 && salary.compareTo(f.maximum) <=0)
                .findFirst()
                .orElse(Track_4);
    }

    public BigDecimal calculate(BigDecimal salary){
        return salary.multiply(aliquot)
                .subtract(deductInstallment)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
