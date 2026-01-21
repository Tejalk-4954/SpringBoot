package com.pgFinder.PgFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgFinder.PgFinder.entity.Pg;

public interface PgRepository extends JpaRepository<Pg, Long> {

}
