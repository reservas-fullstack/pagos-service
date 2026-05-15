package com.fullstack.pagos.controller;

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
    public Pago crearPago(@Valid @RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Long id,
            @Valid @RequestBody Pago pagoActualizado) {

        return pagoService.actualizarPago(id, pagoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
    }
}