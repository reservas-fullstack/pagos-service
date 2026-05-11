package com.fullstack.pagos.controller;

import com.fullstack.pagos.model.Pago;
import com.fullstack.pagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pago> obtenerPorId(@PathVariable Long id) {
        return pagoRepository.findById(id);
    }

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago) {
        return pagoRepository.save(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizarPago(@PathVariable Long id, @RequestBody Pago pagoActualizado) {

        Pago pago = pagoRepository.findById(id).orElse(null);

        if (pago != null) {

            pago.setReservaId(pagoActualizado.getReservaId());
            pago.setMonto(pagoActualizado.getMonto());
            pago.setMetodoPago(pagoActualizado.getMetodoPago());
            pago.setEstado(pagoActualizado.getEstado());
            pago.setFechaPago(pagoActualizado.getFechaPago());

            return pagoRepository.save(pago);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
    }
}