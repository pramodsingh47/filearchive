package com.filedm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filedm.entity.ArchiveFileSystem;

public interface ArchiveRepository extends JpaRepository<ArchiveFileSystem, Long>{

}
