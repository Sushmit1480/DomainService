package com.sushmit.domain.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name="domain")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "domain_name", nullable = false)
	private String domainName;
	
	@Column(name = "domainCode", nullable = false)
	private String domainCode;

	public Domain() {
		super();
	}

	public Domain(String domainName, String domainCode) {
		super();
		this.domainName = domainName;
		this.domainCode = domainCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	@Override
	public String toString() {
		return "Domain [id=" + id + ", domainName=" + domainName + ", domainCode=" + domainCode + "]";
	}
}