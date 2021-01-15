package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class VendingMachineController {

    private static final VendingMachineServiceImpl vendingMachineService = new VendingMachineServiceImpl();

    private VendingMachineData vendingMachineData = new VendingMachineData();

    @GetMapping("/")
    public ModelAndView home(@RequestParam(name="order", required=false, defaultValue="") String orderStatus) {
        vendingMachineData.setInventorySummary(vendingMachineService.fetchInventorySummary());
        ModelMap modelMap = new ModelMap();
        modelMap.put("vendingMachineData", vendingMachineData);
        modelMap.put("orderStatus", orderStatus);
        return new ModelAndView("home", "model", modelMap);
    }

    @RequestMapping("/insert-quarter")
    public ModelAndView submitPayment() {
        vendingMachineData.setCurrentBalance(vendingMachineData.getCurrentBalance() + 0.25);
        ModelMap modelMap = new ModelMap();
        modelMap.put("vendingMachineData", vendingMachineData);
        return new ModelAndView("redirect:/", "model", modelMap);
    }

    @RequestMapping("/remove-quarter")
    public ModelAndView withdrawPayment() {
        vendingMachineData.setCurrentBalance(vendingMachineData.getCurrentBalance() - 0.25);
        ModelMap modelMap = new ModelMap();
        modelMap.put("vendingMachineData", vendingMachineData);
        return new ModelAndView("redirect:/", "model", modelMap);
    }

    @RequestMapping("/order")
    public ModelAndView order(@RequestParam(name="name") String id) {
        try {
            Product purchasedProduct = vendingMachineService.submitOrder(id);
            vendingMachineData.setCurrentBalance(vendingMachineData.getCurrentBalance() - purchasedProduct.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/?order=failure", "model", "");
        }
        ModelMap modelMap = new ModelMap();
        modelMap.put("vendingMachineData", vendingMachineData);
        return new ModelAndView("redirect:/?order=success", "model", modelMap);
    }

}