package com.fullstack.pagos.service;

import com.fullstack.pagos.dto.PagoDTO;
import com.fullstack.pagos.exception.RecursoNoEncontradoException;
import com.fullstack.pagos.model.Pago;
import com.fullstack.pagos.repository.PagoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private static final Logger logger = LoggerFactory.getLogger(PagoService.class);

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listarPagos() {

        logger.info("Listando todos los pagos");

        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {

        logger.info("Buscando pago con ID: {}", id);

        return pagoRepository.findById(id);
    }

    public Pago guardarPago(PagoDTO pagoDTO) {

        logger.info("Creando nuevo pago para reserva ID: {}",
                pagoDTO.getReservaId());

        Pago pago = new Pago();

        pago.setReservaId(pagoDTO.getReservaId());
        pago.setMonto(pagoDTO.getMonto());
        pago.setMetodoPago(pagoDTO.getMetodoPago());
        pago.setEstado(pagoDTO.getEstado());
        pago.setFechaPago(pagoDTO.getFechaPago());

        logger.info("Pago creado correctamente");

        return pagoRepository.save(pago);
    }

    public Pago actualizarPago(Long id, PagoDTO pagoDTO) {

        logger.info("Actualizando pago con ID: {}", id);

        return pagoRepository.findById(id).map(pago -> {

            pago.setReservaId(pagoDTO.getReservaId());
            pago.setMonto(pagoDTO.getMonto());
            pago.setMetodoPago(pagoDTO.getMetodoPago());
            pago.setEstado(pagoDTO.getEstado());
            pago.setFechaPago(pagoDTO.getFechaPago());

            logger.info("Pago actualizado correctamente");

            return pagoRepository.save(pago);

        }).orElseThrow(() -> {

            logger.warn("Pago no encontrado para actualizar. ID: {}", id);

            return new RecursoNoEncontradoException(
                    "Pago no encontrado con ID: " + id);
        });
    }

    public boolean eliminarPago(Long id) {

        logger.info("Intentando eliminar pago con ID: {}", id);

        if (pagoRepository.existsById(id)) {

            pagoRepository.deleteById(id);

            logger.info("Pago eliminado correctamente");

            return true;
        }

        logger.warn("Pago no encontrado para eliminar. ID: {}", id);

        throw new RecursoNoEncontradoException(
                "Pago no encontrado con ID: " + id);
    }
}