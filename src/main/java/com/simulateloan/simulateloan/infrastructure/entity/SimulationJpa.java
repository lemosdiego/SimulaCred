package com.simulateloan.simulateloan.infrastructure.entity;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientJpa client;

    @Column(name = "valor_solicitado", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorSolicitado;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
