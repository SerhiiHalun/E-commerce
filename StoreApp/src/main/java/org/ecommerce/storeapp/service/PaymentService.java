package org.ecommerce.storeapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.storeapp.model.Order;
import org.ecommerce.storeapp.model.Payment;
import org.ecommerce.storeapp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

//    public Payment createPayment(Order order, String paymentMethod, String status) {
//        Payment payment = new Payment();
//        payment.setOrder(order);
//        payment.setPaymentMethod(paymentMethod);
//        payment.setStatus(status);
//        payment.setPaymentDate(LocalDate.now());
//
//        return paymentRepository.save(payment);
//    }
//
//    public Payment updatePayment(int id, Order order, String paymentMethod, String status) {
//        return paymentRepository.findById(id).map(payment -> {
//            if (order != null) {
//                payment.setOrder(order);
//            }
//            if (paymentMethod != null) {
//                payment.setPaymentMethod(paymentMethod);
//            }
//            if (status != null) {
//                payment.setStatus(status);
//            }
//
//            return paymentRepository.save(payment);
//        }).orElseThrow(() -> new RuntimeException("Payment not found"));
//    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }
}
