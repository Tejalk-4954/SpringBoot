package com.company.user_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.user_service.entity.UserRole;
import com.company.user_service.entity.UserRoleId;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    // If UserRole is composite key entity, adjust accordingly (depends on your entity).
    List<UserRole> findByUserId(String userId);
    void deleteByUserIdAndRoleId(String userId, String roleId);
    
    List<UserRole> findByRoleId(String roleId);
}
