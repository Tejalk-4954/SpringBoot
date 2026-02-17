package com.pgfinder.pgfinder_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgfinder.pgfinder_backend.Entity.ChatMessage;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

	List<ChatMessage> findBySenderAndReceiver(String sender, String receiver);
}
