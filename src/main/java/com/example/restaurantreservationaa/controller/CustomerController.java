package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import com.example.restaurantreservationaa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/customers/:customerId")
    public ResponseEntity<Customer> getCustomer(long customerId)  throws CustomerNotFoundException {
        Customer customer = customerService.get(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.add(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/:customerId")
    public ResponseEntity<Void> removeCustomer(long customerId) throws CustomerNotFoundException{
        customerService.remove(customerId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
