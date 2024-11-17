package com.sushmit.domain.service;

import java.util.List;
import java.util.Optional;

import com.sushmit.domain.model.Domain;

public interface DomainService {
	Domain createDomain(Domain domain);
	List<Domain> getAllDomains();
	Optional<Domain> getDomainById(Integer id);
	Domain updateDomain(Integer id, Domain domain);
	void deleteDomain(Integer id);
}