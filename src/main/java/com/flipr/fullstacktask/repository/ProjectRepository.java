package com.flipr.fullstacktask.repository;

import com.flipr.fullstacktask.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // We get save(), findAll(), findById(), deleteById(), etc. for free!
}
