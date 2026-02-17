package com.company.user_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.user_service.entity.FileMetadata;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, String> { }
