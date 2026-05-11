package com.fullstack.pagos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservaId;

    private Double monto;

    private String metodoPago;

    private String estado;

    private LocalDateTime fechaPago;
}
