package com.synechron.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.synechron.assignment.entity.Policy;
import com.synechron.assignment.exception.PolicyNotFoundException;
import com.synechron.assignment.services.PolicyService;
import lombok.Setter;

@RestController
@Setter
@CrossOrigin(origins = "*")
public class PolicyController {

	@Autowired
	private PolicyService service;

	@GetMapping(path = "/api/policy/getAllPolicies")
	public List<Policy> getAllPolicy() {

		return this.service.findAllPolicy();
	}

	@GetMapping("/api/policy/getPolicy/{policyId}")
	public Policy getPolicy(@PathVariable int policyId) {

		Policy policy = service.getPolicy(policyId);

		if (policy == null) {
			System.out.println("Inside Controller  " + policyId);
			throw new PolicyNotFoundException("Policy Id " + policyId + " does not exist");
		}

		return policy;

	}

	@PostMapping(produces = "application/json", consumes = "application/json", path = "/api/policy/addPolicy")
	public Policy add(@RequestBody Policy entity) {

		return this.service.add(entity);
		
	}

	@PutMapping(path = "/api/policy/updatePolicy")
	public Policy update(@RequestBody Policy entity) {

		return this.service.update(entity);

	}

	@DeleteMapping(path = "/api/policy/deletePolicy")
	public Policy delete(@RequestBody Policy entity) {
		try {
			Policy deletedEnity = this.service.delete(entity);
			if (deletedEnity == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Requested entity does not exist");
			}

		} catch (ResponseStatusException e) {
			throw e;
		}

		return entity;
	}

	@DeleteMapping("/api/policy/deletePolicy/{policyId}")
	public String deletePolicy(@PathVariable("policyId") int policyId) {
		return service.deletePolicy(policyId);
	}

}
