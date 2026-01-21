package com.pgFinder.PgFinder.service;


import org.springframework.stereotype.Service;

import com.pgFinder.PgFinder.entity.Pg;
import com.pgFinder.PgFinder.repository.PgRepository;

import java.util.List;

@Service
public class PgService {

    private final PgRepository pgRepository;

    public PgService(PgRepository pgRepository) {
        this.pgRepository = pgRepository;
    }

    public List<Pg> getAllPgs() {
        return pgRepository.findAll();
    }

    public Pg savePg(Pg pg) {
        return pgRepository.save(pg);
    }
}