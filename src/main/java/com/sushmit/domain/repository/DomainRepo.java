package com.sushmit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sushmit.domain.model.Domain;

@Repository
public interface DomainRepo extends JpaRepository<Domain, Integer>{

}
