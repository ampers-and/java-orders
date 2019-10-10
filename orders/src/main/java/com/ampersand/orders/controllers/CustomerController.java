package com.ampersand.orders.controllers;

import com.ampersand.orders.models.Customer;
import com.ampersand.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    //http://localhost:2020/customer/order
    //GET /customer/order - Returns all customers with their orders
    @GetMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustWithOrders()
    {
        List<Customer> myList = customerService.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //http://localhost:2020/customer/new
    //POST /customer/new - Adds a new customer including any new orders
    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer nCustomer)
    {
        customerService.save(nCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //http://localhost:2020/customer/update/:id
    //PUT /customer/update/{custcode} - Updates the customer based off of custcode. Does not have to do anything with Orders!
    @PutMapping(value = "/update/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable long custcode)
    {
        customerService.update(customer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:2020/customer/delete/:id
    //DELETE /customer/delete/{custcode} - Deletes the customer based off of custcode
    //this should also delete the orders of that customer
    @DeleteMapping(value = "/delete/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
