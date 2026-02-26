package com.simulateloan.simulateloan.application.usecases;

import com.simulateloan.simulateloan.application.controllers.dto.request.SimulateLoanRequest;
import com.simulateloan.simulateloan.application.controllers.dto.response.PlansResponse;
import com.simulateloan.simulateloan.application.controllers.dto.response.SimulateLoanResponse;
import com.simulateloan.simulateloan.domain.client.Client;
import com.simulateloan.simulateloan.domain.enums.loan.CreditTrack;
import com.simulateloan.simulateloan.domain.rules.CalculatingNetSalaryRule;
import com.simulateloan.simulateloan.domain.rules.SimulateLoanRule;
import com.simulateloan.simulateloan.domain.simulation.Simulation;
import com.simulateloan.simulateloan.infrastructure.entity.client.ClientJpa;
import com.simulateloan.simulateloan.infrastructure.entity.simulation.SimulationJpa;
import com.simulateloan.simulateloan.infrastructure.repositories.client.ClientRepository;
import com.simulateloan.simulateloan.infrastructure.repositories.simulation.SimulationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulateLoanUseCase {

    private final ClientRepository clientRepository;
    private final SimulationRepository simulationRepository;

    // Injeção de dependência por construtor (boa prática)
    public SimulateLoanUseCase(
            ClientRepository clientRepository,
            SimulationRepository simulationRepository
    ) {
        this.clientRepository = clientRepository;
        this.simulationRepository = simulationRepository;
    }

    // inicia o processo de simulação
    public SimulateLoanResponse process(SimulateLoanRequest request) {

        // Cria o cliente recebendo o salário bruto
        // O salário líquido inicia com 0 e será calculado depois
        Client client = new Client(
                request.nome(),
                request.salarioBruto(),
                BigDecimal.ZERO
        );

        // Chama a regra de cálculo do salário líquido
        CalculatingNetSalaryRule netSalaryRule = new CalculatingNetSalaryRule();
        Client clientNetSalary = netSalaryRule.run(client);

        // Obtém o salário líquido calculado
        BigDecimal netSalary = clientNetSalary.getNetSalary();

        // Após pegar o salário líquido, identifica a faixa de crédito
        CreditTrack track = CreditTrack.trackFor(netSalary);

        // Após descobrir a faixa, gera as simulações:
        // meses / valor da parcela / percentual do salário
        SimulateLoanRule loanRule = new SimulateLoanRule();

        // Opções de parcelas disponíveis para a faixa de crédito
        List<Integer> options = track.getInstallmentOptions();

        // Lista de simulações geradas
        List<Simulation> simulations = new ArrayList<>();

        // Para cada quantidade de parcelas, gera uma simulação
        for (Integer installments : options) {
            Simulation simulation = loanRule.run(netSalary, installments);
            simulations.add(simulation);
        }

        // 👇 SALVA NO BANCO DE DADOS

        // SALVA CLIENTE
        ClientJpa clientJpa = new ClientJpa(
                clientNetSalary.getName(),
                clientNetSalary.getGrossSalary(),
                netSalary
        );

        // Salva e mantém uma referência final (necessário para lambda)
        ClientJpa savedClient = clientRepository.save(clientJpa);

        // SALVA SIMULAÇÕES
        List<SimulationJpa> simJpas = simulations.stream()
                .map(sim -> new SimulationJpa(
                        savedClient.getId(),
                        track.calculateLimit(netSalary),
                        sim.installments,
                        String.format(
                                "%d meses R$%.2f (%.1f%%)",
                                sim.installments,
                                sim.installmentsValue,
                                sim.percentageOfSalary
                        )
                ))
                .collect(Collectors.toList());

        simulationRepository.saveAll(simJpas);

        // Monta o JSON da resposta
        // A resposta contém uma lista de planos
        List<PlansResponse> plans = simulations.stream()
                .map(simulation -> new PlansResponse(
                        simulation.installments,
                        simulation.installmentsValue,
                        simulation.percentageOfSalary,
                        track.interestRate.toString()
                ))
                .toList();

        // Retorna a resposta final com os dados da simulação
        return new SimulateLoanResponse(
                request.nome(),
                netSalary,
                track.name(),
                track.calculateLimit(netSalary),
                plans
        );
    }
}