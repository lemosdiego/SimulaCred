package com.simulateloan.simulateloan.infrastructure.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class ClientJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "salario_bruto", precision = 10, scale = 2)
    private BigDecimal salarioBruto;

    @Column(name = "salario_liquido", precision = 10, scale = 2)
    private BigDecimal salarioLiquido;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
