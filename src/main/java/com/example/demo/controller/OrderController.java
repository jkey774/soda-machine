package com.example.demo.controller;

import com.example.demo.data.enumeration.OrderStatus;
import com.example.demo.model.Product;
import com.example.demo.model.VendingMachine;
import com.example.demo.service.IVendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.example.demo.util.Constants.FORM_URL_ENCODED;
import static com.example.demo.util.Constants.VENDING_MACHINE_MODEL_NAME;

@RestController
@Scope("session")
public class OrderController {

  private final IVendingMachineService vendingMachineService;

  @Autowired private VendingMachine vendingMachine;

  @Autowired
  public OrderController(IVendingMachineService vendingMachineService) {
    this.vendingMachineService = vendingMachineService;
  }

  @PostMapping(path = "/order", consumes = FORM_URL_ENCODED)
  public ModelAndView submitOrder(Product product) {

    String orderStatus = OrderStatus.SUCCESS.getValue();

    try {
      vendingMachineService.submitOrder(product);
      vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() - product.getPrice());
    } catch (Exception e) {
      e.printStackTrace();
      orderStatus = OrderStatus.FAILURE.getValue();
    }

    return new ModelAndView(
        "redirect:/?orderStatus=" + orderStatus, VENDING_MACHINE_MODEL_NAME, vendingMachine);
  }
}
