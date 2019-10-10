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

    //STRETCH
//    @Override
//    public Customer update(List<Order> orders, long id)
//    {
//        Customer nCustomer = custrepo.findById(id).get();
//
//        //Customer constructor
//        new Customer (custrepo.findById(id).get().getCustname(),
//                                            custrepo.findById(id).get().getCustcity(),
//                                            custrepo.findById(id).get().getWorkingarea(),
//                                            custrepo.findById(id).get().getCustcountry(),
//                                            custrepo.findById(id).get().getGrade(),
//                                            custrepo.findById(id).get().getOpeningamt(),
//                                            custrepo.findById(id).get().getReceiveamt(),
//                                            custrepo.findById(id).get().getPaymentamt(),
//                                            custrepo.findById(id).get().getOutstandingamt(),
//                                            custrepo.findById(id).get().getPhone(),
//                                            custrepo.findById(id).get().getAgent() );
//
//        for (Order o: orders)
//        {
//            nCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), nCustomer, o.getOrddescription()));
//        }
//        return custrepo.save(nCustomer);
//    }

    @Override
    public void delete(long id)
    {
        custrepo.deleteById(id);
    }
}
