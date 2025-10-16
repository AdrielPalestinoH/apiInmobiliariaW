package com.example.inmo.services;


import com.example.inmo.models.CreditoDetalle;
import com.example.inmo.repositories.CreditoDetalleRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaypalService {

    private final CreditoDetalleRepository detalleRepo;
    private final APIContext apiContext;

    public PaypalService(CreditoDetalleRepository detalleRepo,
                         @Value("${paypal.client.id}") String clientId,
                         @Value("${paypal.client.secret}") String clientSecret,
                         @Value("${paypal.mode}") String mode) {
        this.detalleRepo = detalleRepo;
        this.apiContext = new APIContext(clientId, clientSecret, mode);
    }

    public Payment crearPago(Long detalleId, String cancelUrl, String successUrl) throws PayPalRESTException {
        CreditoDetalle detalle = detalleRepo.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", detalle.getPrecio()));

        Transaction transaction = new Transaction();
        transaction.setDescription("Pago crédito #" + detalle.getId());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public void confirmarPago(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution execution = new PaymentExecution();
        execution.setPayerId(payerId);
        Payment executedPayment = payment.execute(apiContext, execution);

        if (executedPayment.getState().equals("approved")) {
            // ✅ Aquí actualizas el estado del pago
            String desc = executedPayment.getTransactions().get(0).getDescription();
            Long detalleId = Long.parseLong(desc.replace("Pago crédito #", ""));
            CreditoDetalle detalle = detalleRepo.findById(detalleId).orElseThrow();
            detalle.setEstatus(1);
            detalleRepo.save(detalle);
        }
    }
}
