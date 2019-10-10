package com.ampersand.orders.services;

import com.ampersand.orders.repositories.AgentRepository;
import com.ampersand.orders.services.AgentService;
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
        agentrepo.deleteById(id);
    }
}
