package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class VendingMachineController {

    private static final double PAYMENT_AMOUNT = 0.25;

    private final VendingMachine machine;
    private final VendingMachineService service;

    @Autowired
    public VendingMachineController(VendingMachine machine, VendingMachineService service) {
        this.machine = machine;
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView home(@RequestParam(name="orderStatus", required=false, defaultValue="") String orderStatus) {
        try {
            machine.setProducts(service.fetchProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        machine.setOrderStatus(orderStatus);
        return new ModelAndView("home", "vendingMachine", machine);
    }

    @RequestMapping("/insert-payment")
    public ModelAndView submitPayment() {
        machine.setCurrentBalance(machine.getCurrentBalance() + PAYMENT_AMOUNT);
        return new ModelAndView("redirect:/", "vendingMachine", machine);
    }

    @RequestMapping("/remove-payment")
    public ModelAndView withdrawPayment() {
        machine.setCurrentBalance(machine.getCurrentBalance() - PAYMENT_AMOUNT);
        return new ModelAndView("redirect:/", "vendingMachine", machine);
    }

    @RequestMapping("/order")
    public ModelAndView submitOrder(@RequestParam(name="id") String id) {

        try {
            Product product = service.fetchProduct(id);
            service.submitOrder(product);
            machine.setCurrentBalance(machine.getCurrentBalance() - product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/?orderStatus=failure", "vendingMachine", machine);
        }

        return new ModelAndView("redirect:/?orderStatus=success", "vendingMachine", machine);
    }

}