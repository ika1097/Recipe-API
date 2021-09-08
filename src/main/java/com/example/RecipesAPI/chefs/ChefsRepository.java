package com.example.RecipesAPI.chefs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChefsRepository extends JpaRepository<Chefs, Long> {

    Optional<Chefs> findByEmail(String email);
}
