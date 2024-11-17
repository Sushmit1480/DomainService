package com.sushmit.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sushmit.domain.model.Domain;
import com.sushmit.domain.service.DomainService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/domains")
public class DomainController {

	@Autowired
	private DomainService service;
	
	@Operation(summary = "Create a new domain", description = "Adds a new domain to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/create")
    public ResponseEntity<Integer> createDomain(@RequestParam("domainName") String domainName,
    		@RequestParam("domainCode") String domainCode){
		Domain domain = new Domain(domainName, domainCode);
    	Domain create = service.createDomain(domain);
        return ResponseEntity.ok(create.getId());
    }
	
	@Operation(summary = "Get all domains", description = "Retrieves a list of all domains")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of domains"),
    })
    @GetMapping()
    public List<Domain> getAllDomains() {
        return service.getAllDomains();
    }
	
	@Operation(summary = "Get domain by ID", description = "Retrieves a single domain by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved domain"),
        @ApiResponse(responseCode = "404", description = "Domain not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Domain> getEmployeeById(@PathVariable("id") Integer id) {
        Optional<Domain> domain = service.getDomainById(id);
        return domain.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@Operation(summary = "Update a domain", description = "Updates an existing domain by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain updated successfully"),
        @ApiResponse(responseCode = "404", description = "Domain not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Domain> updateEmployee(@PathVariable("id") Integer id,
                                                   @RequestParam("domainName") String domainName,
                                                   @RequestParam("domainCode") String domainCode) {
        // Retrieve the existing employee details from the database
        Optional<Domain> existingDomainOptional = service.getDomainById(id);
        if (!existingDomainOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Domain existingDomain = existingDomainOptional.get();
        
        // Update the employee details
        existingDomain.setDomainName(domainName);
        existingDomain.setDomainCode(domainCode);
      
        // Save the updated employee
        Domain updatedDomain = service.updateDomain(id, existingDomain);
        return ResponseEntity.ok(updatedDomain);
    }
	
	@Operation(summary = "Delete an domain", description = "Deletes an existing domain by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Domain not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDomain(@PathVariable("id") Integer id) {
        try {
            service.deleteDomain(id);
            return ResponseEntity.ok("Domain with ID: " + id + " has been deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Domain with ID: " + id + " not found.");
        }
    }
}
