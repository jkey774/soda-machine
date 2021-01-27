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

    @Autowired
    private VendingMachineService vendingMachineService;

    @Autowired
    private VendingMachine vendingMachine;

    @GetMapping("/")
    public ModelAndView home(@RequestParam(name="orderStatus", required=false, defaultValue="") String orderStatus) {
        try {
            vendingMachine.setProducts(vendingMachineService.fetchProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        vendingMachine.setOrderStatus(orderStatus);
        return new ModelAndView("home", "vendingMachine", vendingMachine);
    }

    @RequestMapping("/insert-payment")
    public ModelAndView submitPayment() {
        vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() + PAYMENT_AMOUNT);
        return new ModelAndView("redirect:/", "vendingMachine", vendingMachine);
    }

    @RequestMapping("/remove-payment")
    public ModelAndView withdrawPayment() {
        vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() - PAYMENT_AMOUNT);
        return new ModelAndView("redirect:/", "vendingMachine", vendingMachine);
    }

    @RequestMapping("/order")
    public ModelAndView submitOrder(@RequestParam(name="id") String id) {

        try {
            Product product = vendingMachineService.fetchProduct(id);
            vendingMachineService.submitOrder(product);
            vendingMachine.setCurrentBalance(vendingMachine.getCurrentBalance() - product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/?orderStatus=failure", "vendingMachine", vendingMachine);
        }

        return new ModelAndView("redirect:/?orderStatus=success", "vendingMachine", vendingMachine);
    }

}