package com.kapturecx.supportticketsystem.dto;

import java.time.LocalDateTime;

import com.kapturecx.supportticketsystem.model.TicketStatus;

public class Responses {

	public record AgentResponse(Long id, String name, String email, boolean active, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
	}

	public record TicketResponse(Long id, String subject, String description, String requesterEmail,
			TicketStatus status, Long assignedAgentId, String assignedAgentName, String assignedAgentEmail,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
	}
}
