package com.company.user_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.user_service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
Role findByName(String name);

    @Query("SELECT r FROM Role r JOIN UserRole ur ON r.id = ur.roleId WHERE ur.userId = :userId")
    List<Role> findRolesByUserId(@Param("userId") String userId);
    
    
    
   
    	//List<Role> findRolesByUserId(@Param("userId") String userId);
}
