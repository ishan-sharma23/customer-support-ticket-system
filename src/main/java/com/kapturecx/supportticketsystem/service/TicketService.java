package com.kapturecx.supportticketsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kapturecx.supportticketsystem.dto.Responses.TicketResponse;
import com.kapturecx.supportticketsystem.dto.TicketRequests.AssignTicketRequest;
import com.kapturecx.supportticketsystem.dto.TicketRequests.CreateTicketRequest;
import com.kapturecx.supportticketsystem.dto.TicketRequests.UpdateTicketStatusRequest;
import com.kapturecx.supportticketsystem.exception.ResourceNotFoundException;
import com.kapturecx.supportticketsystem.model.Agent;
import com.kapturecx.supportticketsystem.model.Ticket;
import com.kapturecx.supportticketsystem.model.TicketStatus;
import com.kapturecx.supportticketsystem.repository.AgentRepository;
import com.kapturecx.supportticketsystem.repository.TicketRepository;

@Service
@Transactional
public class TicketService {

	private final TicketRepository ticketRepository;
	private final AgentRepository agentRepository;

	public TicketService(TicketRepository ticketRepository, AgentRepository agentRepository) {
		this.ticketRepository = ticketRepository;
		this.agentRepository = agentRepository;
	}

	public TicketResponse createTicket(CreateTicketRequest request) {
		Ticket ticket = new Ticket(request.subject(), request.description(), request.requesterEmail());
		return toResponse(ticketRepository.save(ticket));
	}

	@Transactional(readOnly = true)
	public List<TicketResponse> getTickets() {
		return ticketRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public TicketResponse getTicket(Long id) {
		return toResponse(findTicket(id));
	}

	public TicketResponse assignTicket(Long ticketId, AssignTicketRequest request) {
		Ticket ticket = findTicket(ticketId);
		Agent agent = agentRepository.findById(request.agentId())
				.orElseThrow(() -> new ResourceNotFoundException("Agent not found with id " + request.agentId()));
		if (!agent.isActive()) {
			throw new IllegalStateException("Cannot assign an inactive agent");
		}
		ticket.setAssignedAgent(agent);
		ticket.setStatus(TicketStatus.ASSIGNED);
		return toResponse(ticketRepository.save(ticket));
	}

	public TicketResponse updateStatus(Long ticketId, UpdateTicketStatusRequest request) {
		Ticket ticket = findTicket(ticketId);
		ticket.setStatus(request.status());
		return toResponse(ticketRepository.save(ticket));
	}

	@Transactional(readOnly = true)
	public Ticket findTicket(Long id) {
		return ticketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + id));
	}

	private TicketResponse toResponse(Ticket ticket) {
		Long assignedAgentId = ticket.getAssignedAgent() == null ? null : ticket.getAssignedAgent().getId();
		String assignedAgentName = ticket.getAssignedAgent() == null ? null : ticket.getAssignedAgent().getName();
		String assignedAgentEmail = ticket.getAssignedAgent() == null ? null : ticket.getAssignedAgent().getEmail();
		return new TicketResponse(ticket.getId(), ticket.getSubject(), ticket.getDescription(), ticket.getRequesterEmail(),
				ticket.getStatus(), assignedAgentId, assignedAgentName, assignedAgentEmail, ticket.getCreatedAt(),
				ticket.getUpdatedAt());
	}
}
