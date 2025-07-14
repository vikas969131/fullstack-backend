package com.flipr.fullstacktask.repository;

import com.flipr.fullstacktask.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    boolean existsByEmail(String email);   // handy for custom checks
}
