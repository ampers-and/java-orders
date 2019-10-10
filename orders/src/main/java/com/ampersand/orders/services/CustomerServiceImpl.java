package com.ampersand.orders.services;

import com.ampersand.orders.models.Customer;
import com.ampersand.orders.models.Order;
import com.ampersand.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository custrepo;

    @Override
    public List<Customer> findAll()
    {
        List<Customer> rtnList = new ArrayList<>();
        custrepo.findAll()
                .iterator()
                .forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Customer findById(long id)
    {
        return custrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found" + id));
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer nCustomer = new Customer();

        nCustomer.setCustname(customer.getCustname());
        nCustomer.setCustcity(customer.getCustcity());
        nCustomer.setWorkingarea(customer.getWorkingarea());
        nCustomer.setCustcountry(customer.getCustcountry());
        nCustomer.setGrade(customer.getGrade());
        nCustomer.setOpeningamt(customer.getOpeningamt());
        nCustomer.setReceiveamt(customer.getReceiveamt());
        nCustomer.setPaymentamt(customer.getPaymentamt());
        nCustomer.setOutstandingamt(customer.getOutstandingamt());
        nCustomer.setPhone(customer.getPhone());
        nCustomer.setAgent(customer.getAgent());

        for (Order o : customer.getOrders())
        {
            nCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), nCustomer, o.getOrddescription()));
        }

        return custrepo.save(nCustomer);

    }

    @Override
    public Customer update(Customer customer, long id)
    {
        return custrepo.save(customer);
    }

    @Override
    public void delete(long id)
    {
        custrepo.deleteById(id);
    }
}
