package com.kapturecx.supportticketsystem.dto;

import com.kapturecx.supportticketsystem.model.TicketStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketRequests {

	public record CreateTicketRequest(
			@NotBlank(message = "Subject is required") String subject,
			@NotBlank(message = "Description is required") String description,
			@NotBlank(message = "Requester email is required") @Email(message = "Requester email must be valid") String requesterEmail) {
	}

	public record AssignTicketRequest(@NotNull(message = "Agent id is required") Long agentId) {
	}

	public record UpdateTicketStatusRequest(@NotNull(message = "Ticket status is required") TicketStatus status) {
	}
}
