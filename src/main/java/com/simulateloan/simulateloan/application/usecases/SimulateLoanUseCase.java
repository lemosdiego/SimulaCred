package com.simulateloan.simulateloan.application.usecases;

import com.simulateloan.simulateloan.domain.client.Client;
import com.simulateloan.simulateloan.domain.rules.CalculatingNetSalaryRule;


import java.math.BigDecimal;

public class SimulateLoanSalaryUseCase {
    public void run(){
        Client client= new Client(new BigDecimal("1621.00"), BigDecimal.ZERO);
        com.simulateloan.simulateloan.domain.rules.CalculatingNetSalaryRule rule= new CalculatingNetSalaryRule();
        Client result = rule.run(client);

        System.out.println("Salario Liquido: R$" + result.getNetSalary());
    }

}
