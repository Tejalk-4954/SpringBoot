package com.DaoAcess.DAOacess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DaoAcess.DAOacess.Entity.userEntity;

public interface userRepo extends JpaRepository<userEntity, Integer> {

}
