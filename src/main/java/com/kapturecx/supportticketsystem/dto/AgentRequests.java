package com.kapturecx.supportticketsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AgentRequests {

	public record CreateAgentRequest(
			@NotBlank(message = "Agent name is required") String name,
			@NotBlank(message = "Agent email is required") @Email(message = "Agent email must be valid") String email) {
	}
}
