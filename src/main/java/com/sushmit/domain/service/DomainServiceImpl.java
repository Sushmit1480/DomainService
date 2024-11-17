package com.sushmit.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushmit.domain.model.Domain;
import com.sushmit.domain.repository.DomainRepo;

@Service
public class DomainServiceImpl implements DomainService{
	
	@Autowired
	private DomainRepo repo;

	@Override
	public Domain createDomain(Domain domain) {
		return repo.save(domain);
	}

	@Override
	public List<Domain> getAllDomains() {
		return repo.findAll();
	}

	@Override
	public Optional<Domain> getDomainById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public Domain updateDomain(Integer id, Domain domain) {
		Optional<Domain> dom = repo.findById(id);
		if(dom.isPresent()) {
			Domain update = dom.get();
			update.setDomainName(domain.getDomainName());
			update.setDomainCode(domain.getDomainCode());
			return repo.save(update);
		}else {
			throw new RuntimeException("Domain not found with id: " + id);
		}
	}
	
	@Override
	public void deleteDomain(Integer id) {
		Optional<Domain> domain = repo.findById(id);
		if(domain.isPresent()) {
			repo.delete(domain.get());
		}else {
			throw new RuntimeException("Domain not found with id: " + id);
		}
	}
}
