package com.simulateloan.simulateloan.infrastructure.entity.simulation;

import com.simulateloan.simulateloan.infrastructure.entity.client.ClientJpa;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "simulations")
@EntityListeners(AuditingEntityListener.class)
public class SimulationJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpa client;

    @Column(name = "valor_solicitado", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorSolicitado;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "score")
    private Integer score;

    @Column(name = "resultado", nullable = false)
    private String resultado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 🔴 Construtor padrão (OBRIGATÓRIO para o JPA)
    protected SimulationJpa() {
    }

    // ✅ Construtor usado pelo UseCase
    public SimulationJpa(
            UUID clientId,
            BigDecimal valorSolicitado,
            Integer prazoMeses,
            String resultado
    ) {
        this.clientId = clientId;
        this.valorSolicitado = valorSolicitado;
        this.prazoMeses = prazoMeses;
        this.resultado = resultado;
        this.createdAt = LocalDateTime.now();
    }

    // Getters e setters

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public ClientJpa getClient() {
        return client;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public Integer getScore() {
        return score;
    }

    public String getResultado() {
        return resultado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}