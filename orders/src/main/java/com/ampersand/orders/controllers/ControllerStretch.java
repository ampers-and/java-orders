package com.ampersand.orders.controllers;

import com.ampersand.orders.models.Customer;
import com.ampersand.orders.models.Order;
import com.ampersand.orders.services.AgentService;
import com.ampersand.orders.services.CustomerService;
import com.ampersand.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerStretch
{
    @Autowired
    private AgentService agentService;
    private CustomerService customerService;
    private OrderService orderService;

    //http://localhost:2020/agent/:{id}
    //DELETE /agent/{agentcode} - Deletes an agent if they are not assigned to a customer
    @DeleteMapping(value = "/agent/{agentcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long agentcode)
    {
        agentService.delete(agentcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2020/data/customer/update/:{id}
    //PUT /data/customer/update/{custcode} - update this endpoint to add any orders sent to the endpoint
    @PutMapping(value = "/data/customer/update/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateData(@RequestBody Order order)
    {
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    // http://localhost:2020/data/customer/update/:{id}
//    //PUT /data/customer/update/{custcode} - update this endpoint to add any orders sent to the endpoint
//    @PutMapping(value = "/data/customer/update/{custcode}", consumes = {"application/json"})
//    public ResponseEntity<?> updateData(@RequestBody Order order, @PathVariable long custcode)
//    {
//        orderService.save(order, custcode);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // http://localhost:2020/data/customer/update/:{id}
    //PUT /data/customer/update/{custcode} - update this endpoint to add any orders sent to the endpoint
//    @PutMapping(value = "/data/customer/update/{custcode}", consumes = {"application/json"})
//    public ResponseEntity<?> updateData(@RequestBody List<Order> orders, @PathVariable long custcode)
//    {
//        customerService.update(orders, custcode);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
