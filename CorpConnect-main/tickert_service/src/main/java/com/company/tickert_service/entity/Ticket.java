package com.company.tickert_service.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tickets",
       indexes = {
         @Index(columnList = "department_id"),
         @Index(columnList = "raised_by"),
         @Index(columnList = "assigned_to"),
         @Index(columnList = "status"),
         @Index(columnList = "priority"),
         @Index(columnList = "ticket_number")
       })
@Data
public class Ticket {
    @Id
    @Column(length = 36)
    private String id;

    @Column(name="ticket_number", unique=true, nullable=false)
    private String ticketNumber;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(name="department_id", length=36, nullable=false)
    private String departmentId;

    @Column(nullable=false, length=20)
    private String priority;

    @Column(nullable=false, length=30)
    private String status;

    @Column(name="raised_by", length=36, nullable=false)
    private String raisedBy;

    @Column(name="assigned_to", length=36)
    private String assignedTo;

    @Column(name="created_at", nullable=false)
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @Column(name="closed_at")
    private Instant closedAt;

    @Column(name="sla_due_at")
    private Instant slaDueAt;

    @Column(name="is_deleted")
    private Boolean deleted = Boolean.FALSE;

//    @PrePersist
//    public void prePersist() {
//        if (this.id == null) this.id = UUID.randomUUID().toString();
//        this.createdAt = Instant.now();
//    }
//
//    @PrePersist
//    public void preUpdate() {
//        this.updatedAt = Instant.now();
//    }
    
    @PrePersist
    public void beforeInsert() {
        this.createdAt = Instant.now();
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    public void beforeUpdate() { this.updatedAt = Instant.now(); }

	public Ticket() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Instant getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Instant closedAt) {
		this.closedAt = closedAt;
	}

	public Instant getSlaDueAt() {
		return slaDueAt;
	}

	public void setSlaDueAt(Instant slaDueAt) {
		this.slaDueAt = slaDueAt;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
    
    

    

//	public Ticket(String id, String ticketNumber, String title, String description, String departmentId,
//			String priority, String status, String raisedBy, String assignedTo, Instant createdAt, Instant updatedAt,
//			Instant closedAt, Instant slaDueAt, Boolean deleted) {
//		super();
//		this.id = id;
//		this.ticketNumber = ticketNumber;
//		this.title = title;
//		this.description = description;
//		this.departmentId = departmentId;
//		this.priority = priority;
//		this.status = status;
//		this.raisedBy = raisedBy;
//		this.assignedTo = assignedTo;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.closedAt = closedAt;
//		this.slaDueAt = slaDueAt;
//		this.deleted = deleted;
//	}
//    
    
    
}
