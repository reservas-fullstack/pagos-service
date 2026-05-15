package com.fullstack.pagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoDTO {

    @NotNull(message = "La reserva es obligatoria")
    private Long reservaId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDateTime fechaPago;
}