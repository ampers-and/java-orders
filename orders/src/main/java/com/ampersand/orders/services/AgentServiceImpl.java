package com.ampersand.orders.services;

import com.ampersand.orders.models.Agent;
import com.ampersand.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    private AgentRepository agentrepo;

    @Override
    public void delete(long id)
    {
        Agent agent = agentrepo.findById(id).get();

        if(agent.getCustomers().size() == 0)
        {
            agentrepo.deleteById(id);
        }
    }
}
