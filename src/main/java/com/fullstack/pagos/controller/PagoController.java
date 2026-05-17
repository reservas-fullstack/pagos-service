package com.fullstack.pagos.controller;

import com.fullstack.pagos.dto.PagoDTO;
import com.fullstack.pagos.exception.RecursoNoEncontradoException;
import com.fullstack.pagos.model.Pago;
import com.fullstack.pagos.service.PagoService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodos() {
        return ResponseEntity.ok(pagoService.listarPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable Long id) {

        Pago pago = pagoService.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Pago no encontrado con ID: " + id));

        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(
            @Valid @RequestBody PagoDTO pagoDTO) {

        Pago pagoCreado = pagoService.guardarPago(pagoDTO);

        return ResponseEntity.status(201).body(pagoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(
            @PathVariable Long id,
            @Valid @RequestBody PagoDTO pagoDTO) {

        Pago pagoActualizado = pagoService.actualizarPago(id, pagoDTO);

        return ResponseEntity.ok(pagoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {

        pagoService.eliminarPago(id);

        return ResponseEntity.noContent().build();
    }
}