package com.kapturecx.supportticketsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapturecx.supportticketsystem.dto.AgentRequests.CreateAgentRequest;
import com.kapturecx.supportticketsystem.dto.Responses.AgentResponse;
import com.kapturecx.supportticketsystem.service.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

	private final AgentService agentService;

	public AgentController(AgentService agentService) {
		this.agentService = agentService;
	}

	@PostMapping
	public ResponseEntity<AgentResponse> createAgent(@Valid @RequestBody CreateAgentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(agentService.createAgent(request));
	}

	@GetMapping
	public List<AgentResponse> getAgents() {
		return agentService.getAgents();
	}

	@GetMapping("/{id}")
	public AgentResponse getAgent(@PathVariable Long id) {
		return agentService.getAgent(id);
	}
}
