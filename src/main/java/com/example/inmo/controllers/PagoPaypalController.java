package com.example.inmo.controllers;



import com.example.inmo.services.PaypalService;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos/paypal")
@CrossOrigin(origins = "http://localhost:4200")
public class PagoPaypalController {

    private final PaypalService paypalService;

    @Value("${paypal.success.url}")
    private String successUrl;

    @Value("${paypal.cancel.url}")
    private String cancelUrl;

    public PagoPaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/{detalleId}")
    public String pagar(@PathVariable Long detalleId) {
        try {
            Payment payment = paypalService.crearPago(detalleId, cancelUrl, successUrl);
            for (var link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "{\"approvalUrl\": \"" + link.getHref() + "\"}";
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "{\"error\": \"No se pudo crear el pago\"}";
    }

    @GetMapping("/success")
    public String confirmarPago(@RequestParam("paymentId") String paymentId,
                                @RequestParam("PayerID") String payerId) {
        try {
            paypalService.confirmarPago(paymentId, payerId);
            return "Pago completado correctamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al confirmar el pago.";
        }
    }

    @GetMapping("/cancel")
    public String cancelarPago() {
        return "Pago cancelado por el usuario.";
    }
}
