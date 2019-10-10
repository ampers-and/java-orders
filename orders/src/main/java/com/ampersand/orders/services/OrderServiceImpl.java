package com.ampersand.orders.services;

import com.ampersand.orders.models.Customer;
import com.ampersand.orders.models.Order;
import com.ampersand.orders.repositories.CustomerRepository;
import com.ampersand.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderrepo;
    private CustomerRepository custrepo;

    @Transactional
    @Override
    public Order save(Order order)
    {
//        Customer nCustomer = custrepo.findById(id).get();

        Order nOrder = new Order();

        nOrder.setOrdamount(order.getOrdamount());
        nOrder.setAdvanceamount(order.getAdvanceamount());
        nOrder.setCustomer(order.getCustomer());
//        nOrder.setCustomer(nCustomer);
        nOrder.setOrddescription(order.getOrddescription());

        return orderrepo.save(nOrder);
    }
}
