package com.fullstack.pagos.controller;

import com.fullstack.pagos.dto.PagoDTO;
import com.fullstack.pagos.model.Pago;
import com.fullstack.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoService.listarPagos();
    }

    @GetMapping("/{id}")
    public Optional<Pago> obtenerPorId(@PathVariable Long id) {
        return pagoService.buscarPorId(id);
    }

    @PostMapping
    public Pago crearPago(@Valid @RequestBody PagoDTO pagoDTO) {
        return pagoService.guardarPago(pagoDTO);
    }

    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Long id,
            @Valid @RequestBody PagoDTO pagoDTO) {

        return pagoService.actualizarPago(id, pagoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
    }
}