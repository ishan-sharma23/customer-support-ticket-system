package com.kapturecx.supportticketsystem.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapturecx.supportticketsystem.dto.Responses.TicketResponse;
import com.kapturecx.supportticketsystem.dto.TicketRequests.AssignTicketRequest;
import com.kapturecx.supportticketsystem.dto.TicketRequests.CreateTicketRequest;
import com.kapturecx.supportticketsystem.dto.TicketRequests.UpdateTicketStatusRequest;
import com.kapturecx.supportticketsystem.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping
	public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(request));
	}

	@GetMapping
	public Page<TicketResponse> getTickets(
			@RequestParam(required = false) String status,
			@RequestParam(required = false) Long agentId,
			@RequestParam(required = false) String requesterEmail,
			@RequestParam(required = false) String subject,
			@PageableDefault(size = 20) Pageable pageable) {
		return ticketService.getTickets(status, agentId, requesterEmail, subject, pageable);
	}

	@GetMapping("/{id}")
	public TicketResponse getTicket(@PathVariable Long id) {
		return ticketService.getTicket(id);
	}

	@PostMapping("/{id}/assign")
	public TicketResponse assignTicket(@PathVariable Long id, @Valid @RequestBody AssignTicketRequest request) {
		return ticketService.assignTicket(id, request);
	}

	@PatchMapping("/{id}/status")
	public TicketResponse updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateTicketStatusRequest request) {
		return ticketService.updateStatus(id, request);
	}
}
