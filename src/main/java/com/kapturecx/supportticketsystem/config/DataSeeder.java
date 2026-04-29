package com.kapturecx.supportticketsystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kapturecx.supportticketsystem.model.Agent;
import com.kapturecx.supportticketsystem.repository.AgentRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	private final AgentRepository agentRepository;

	public DataSeeder(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;
	}

	@Override
	public void run(String... args) {
		if (agentRepository.count() == 0) {
			agentRepository.save(new Agent("Asha Mehta", "asha.mehta@kapturecx.com"));
			agentRepository.save(new Agent("Imran Khan", "imran.khan@kapturecx.com"));
		}
	}
}
