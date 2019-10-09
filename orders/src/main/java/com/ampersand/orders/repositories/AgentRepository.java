package com.ampersand.orders.repositories;

import com.ampersand.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long>
{
}
