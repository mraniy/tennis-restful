package com.kata.tennis.repository;


import com.kata.tennis.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {
}
