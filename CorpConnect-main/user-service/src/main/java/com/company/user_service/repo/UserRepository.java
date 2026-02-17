package com.company.user_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.user_service.entity.User;

import jakarta.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    java.util.Optional<com.company.user_service.entity.User> findByEmail(String email);
    
    List<User> findByDepartment(String department);

 // find all employees (non-head roles) in a department using role join
    @Query("SELECT u FROM User u WHERE u.department = :dept AND EXISTS (" +
            "SELECT ur FROM UserRole ur JOIN Role r ON ur.roleId = r.id " +
            "WHERE ur.userId = u.id AND r.name = 'EMPLOYEE')")
     List<User> findEmployeesByDepartment(@Param("dept") String department);

    
 // find all users who are department heads (roles: MANAGER, HR_HEAD, ITS_HEAD)
    @Query("SELECT DISTINCT u FROM User u, UserRole ur, Role r WHERE u.id = ur.userId AND ur.roleId = r.id " +
           "AND r.name IN ('ROLE_MANAGER','ROLE_HR','ROlE_ITS_HEAD','ROLE_DEPT_HEAD')")
    List<User> findDepartmentHeads();

    // find user by id quick
    Optional<User> findById(String id);
    
    @Query("SELECT u.department FROM User u WHERE u.email = :username")
    String findDepartmentByUserId(String username);
    
 

        @Modifying
        @Transactional
        @Query("UPDATE User u SET u.department = :department WHERE u.id = :id")
        int updateDepartment(@Param("id") String id, @Param("department") String department);
    

}
