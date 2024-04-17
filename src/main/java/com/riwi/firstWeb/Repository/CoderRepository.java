package com.riwi.firstWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.firstWeb.Entity.Coder;

@Repository
public interface CoderRepository extends JpaRepository<Coder, Long> {
  
}
