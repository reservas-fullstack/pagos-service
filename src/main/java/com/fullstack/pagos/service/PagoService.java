package com.fullstack.pagos.service;

import com.fullstack.pagos.dto.PagoDTO;
import com.fullstack.pagos.model.Pago;
import com.fullstack.pagos.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago guardarPago(PagoDTO pagoDTO) {

        Pago pago = new Pago();

        pago.setReservaId(pagoDTO.getReservaId());
        pago.setMonto(pagoDTO.getMonto());
        pago.setMetodoPago(pagoDTO.getMetodoPago());
        pago.setEstado(pagoDTO.getEstado());
        pago.setFechaPago(pagoDTO.getFechaPago());

        return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Long id, PagoDTO pagoDTO) {

        return pagoRepository.findById(id).map(pago -> {

            pago.setReservaId(pagoDTO.getReservaId());
            pago.setMonto(pagoDTO.getMonto());
            pago.setMetodoPago(pagoDTO.getMetodoPago());
            pago.setEstado(pagoDTO.getEstado());
            pago.setFechaPago(pagoDTO.getFechaPago());

            return pagoRepository.save(pago);

        }).orElse(null);
    }

    public boolean eliminarPago(Long id) {

        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}