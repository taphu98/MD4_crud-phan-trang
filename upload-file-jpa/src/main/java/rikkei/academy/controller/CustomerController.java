package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Customer;
import rikkei.academy.service.ICustomerService;
import rikkei.academy.validate.ValidateUsername;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @Autowired
    ValidateUsername validateUsername;
    @GetMapping("/customer")
    public ModelAndView showList(@PageableDefault(sort = "name", size = 3) Pageable pageable ) {
        Page<Customer> customerPage = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/index");
        modelAndView.addObject("customers", customerPage);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public String createCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        validateUsername.validate(customer,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/customer/create";
        }

        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id){
        customerService.deleteById(id);
        return "redirect:/customer";
    }
    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("search")String search, Pageable pageable){
        Page<Customer> customerPage;
        if (!search.trim().equals("")){
            customerPage = customerService.findByNameCustomer(search,pageable);
        }else {
            customerPage = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/index");
        modelAndView.addObject("pageCustomer",customerPage);
        return modelAndView;
    }

}
