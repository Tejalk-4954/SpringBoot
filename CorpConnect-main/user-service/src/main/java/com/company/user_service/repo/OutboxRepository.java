package com.company.user_service.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.user_service.entity.OutboxEvent;

import jakarta.transaction.Transactional;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEvent, String> {
    List<OutboxEvent> findByStatusOrderByCreatedAtAsc(String status);
//
//    @Modifying
//    @Query("UPDATE OutboxEvent e SET e.status = :status, e.publishAttempts = e.publishAttempts + 1, e.publishedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
//    void markPublished(@Param("id") String id, @Param("status") String status);
//}
    
    @Modifying
    @Transactional
    @Query("UPDATE OutboxEvent e SET e.status = :status, e.publishAttempts = e.publishAttempts + 1, e.publishedAt = :publishedAt WHERE e.id = :id")
    void markPublished(@Param("id") String id, @Param("status") String status, @Param("publishedAt") Instant publishedAt);
}