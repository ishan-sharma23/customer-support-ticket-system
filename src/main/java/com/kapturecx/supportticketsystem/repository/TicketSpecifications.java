package com.kapturecx.supportticketsystem.repository;

import org.springframework.data.jpa.domain.Specification;

import com.kapturecx.supportticketsystem.model.Agent;
import com.kapturecx.supportticketsystem.model.Ticket;
import com.kapturecx.supportticketsystem.model.TicketStatus;

public final class TicketSpecifications {

    private TicketSpecifications() {}

    public static Specification<Ticket> hasStatus(TicketStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Ticket> hasAssignedAgentId(Long agentId) {
        return (root, query, cb) -> agentId == null ? null : cb.equal(root.get("assignedAgent").get("id"), agentId);
    }

    public static Specification<Ticket> requesterEmailContains(String requesterEmail) {
        return (root, query, cb) -> {
            if (requesterEmail == null || requesterEmail.isBlank()) return null;
            return cb.like(cb.lower(root.get("requesterEmail")), "%" + requesterEmail.toLowerCase() + "%");
        };
    }

    public static Specification<Ticket> subjectContains(String subject) {
        return (root, query, cb) -> {
            if (subject == null || subject.isBlank()) return null;
            return cb.like(cb.lower(root.get("subject")), "%" + subject.toLowerCase() + "%");
        };
    }
}
