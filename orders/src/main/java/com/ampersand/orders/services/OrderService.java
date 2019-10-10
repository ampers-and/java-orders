package com.ampersand.orders.services;

import com.ampersand.orders.models.Order;

public interface OrderService
{
    Order save(Order order);
//    Order save(Order order, long id);
}
