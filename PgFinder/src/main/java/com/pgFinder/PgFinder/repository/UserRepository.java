package com.pgFinder.PgFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgFinder.PgFinder.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
