package com.example.demospring.demomanyto_many.repository;

import com.example.demospring.demomanyto_many.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
