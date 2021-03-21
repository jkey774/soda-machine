package com.example.demo.controller;

import com.example.demo.service.IPaymentService;
import com.example.demo.model.Payment;
import com.example.demo.model.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.example.demo.util.Constants.FORM_URL_ENCODED;
import static com.example.demo.util.Constants.VENDING_MACHINE_MODEL_NAME;

@RestController
@Scope("session")
@RequestMapping("/payments")
public class PaymentController {

  private final IPaymentService paymentService;

  @Autowired private VendingMachine vendingMachine;

  @Autowired
  public PaymentController(IPaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping(path = "/deposit", consumes = FORM_URL_ENCODED)
  public ModelAndView depositPayment(Payment payment) {
    paymentService.deposit(payment);
    return new ModelAndView("redirect:/", VENDING_MACHINE_MODEL_NAME, vendingMachine);
  }

  @PostMapping(path = "/withdraw", consumes = FORM_URL_ENCODED)
  public ModelAndView withdrawPayment(Payment payment) {
    paymentService.withdraw(payment);
    return new ModelAndView("redirect:/", VENDING_MACHINE_MODEL_NAME, vendingMachine);
  }
}
