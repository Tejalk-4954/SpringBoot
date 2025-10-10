package com.DTO.DtoDemo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DTO.DtoDemo.Entity.Actor;

public interface ActorRepo extends JpaRepository<Actor, Integer> {

//	@Query(value="select * from actor where Aname="ak")
//	public List<Actor> findByName(String name);
}
