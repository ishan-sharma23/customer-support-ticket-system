package com.kapturecx.supportticketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapturecx.supportticketsystem.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
