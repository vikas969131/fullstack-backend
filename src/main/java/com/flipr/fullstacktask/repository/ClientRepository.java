package com.flipr.fullstacktask.repository;

import com.flipr.fullstacktask.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // All CRUD methods are inherited
}
