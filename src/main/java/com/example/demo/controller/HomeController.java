package com.example.demo.controller;

import com.example.demo.data.enumeration.OrderStatus;
import com.example.demo.model.VendingMachine;
import com.example.demo.service.IVendingMachineService;
import com.example.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class HomeController {

  private final IVendingMachineService vendingMachineService;

  @Autowired private VendingMachine vendingMachine;

  @Autowired
  public HomeController(IVendingMachineService vendingMachineService) {
    this.vendingMachineService = vendingMachineService;
  }

  @GetMapping(path = "/")
  public ModelAndView homePage(
      @RequestParam(name = Constants.ORDER_STATUS, required = false, defaultValue = "")
          String orderStatus) {
    try {
      vendingMachine.setProducts(vendingMachineService.fetchProducts());
    } catch (Exception e) {
      e.printStackTrace();
    }
    vendingMachine.setOrderStatus(OrderStatus.of(orderStatus).getValue());
    return new ModelAndView("home", "vendingMachine", vendingMachine);
  }
}
