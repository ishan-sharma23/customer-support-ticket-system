package com.kapturecx.supportticketsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kapturecx.supportticketsystem.dto.AgentRequests.CreateAgentRequest;
import com.kapturecx.supportticketsystem.dto.Responses.AgentResponse;
import com.kapturecx.supportticketsystem.exception.ResourceNotFoundException;
import com.kapturecx.supportticketsystem.model.Agent;
import com.kapturecx.supportticketsystem.repository.AgentRepository;

@Service
@Transactional
public class AgentService {

	private final AgentRepository agentRepository;

	public AgentService(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;
	}

	public AgentResponse createAgent(CreateAgentRequest request) {
		Agent agent = new Agent(request.name(), request.email());
		return toResponse(agentRepository.save(agent));
	}

	@Transactional(readOnly = true)
	public List<AgentResponse> getAgents() {
		return agentRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public AgentResponse getAgent(Long id) {
		return toResponse(findAgent(id));
	}

	@Transactional(readOnly = true)
	public Agent findAgent(Long id) {
		return agentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found with id " + id));
	}

	private AgentResponse toResponse(Agent agent) {
		return new AgentResponse(agent.getId(), agent.getName(), agent.getEmail(), agent.isActive(), agent.getCreatedAt(),
				agent.getUpdatedAt());
	}
}
