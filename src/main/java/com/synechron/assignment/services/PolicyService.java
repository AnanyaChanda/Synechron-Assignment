package com.synechron.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synechron.assignment.entity.Policy;
import com.synechron.assignment.exception.PolicyNotFoundException;
import com.synechron.assignment.repos.PolicyRepository;

import lombok.Setter;

@Service
@Setter
public class PolicyService {

	@Autowired
	private PolicyRepository repo;

	public List<Policy> findAllPolicy() {

		List<Policy> policyList = repo.findAll();
		if (policyList.isEmpty()) {
			throw new PolicyNotFoundException("No Policies available in database ");
		}
		return policyList;
	}

	public Policy getPolicy(int policyId) throws PolicyNotFoundException {

		Policy policy = null;

		Optional<Policy> result = repo.findById(policyId);

		if (result.isPresent()) {

			policy = result.get();
		} else {
			List<Policy> policyList = repo.findAll();

			if (policyList.isEmpty()) 
			{
				throw new PolicyNotFoundException("No Policies available in database");
			}

			throw new PolicyNotFoundException("Policy Id " + policyId + " does not exist");
		}
		return policy;

	}

	public Policy add(Policy entity) {
		return this.repo.save(entity);

	}

	public Policy update(Policy entity) {
		return this.repo.save(entity);
	}

	public Policy delete(Policy entity) {
		Policy policy = null;

		if (repo.existsById(entity.getPolicyId())) {

			this.repo.delete(entity);
			policy = entity;
		}
		return policy;
	}

	public String deletePolicy(int id) {
		String message = "Policy with Id " + id + " deleted successfully";
		repo.findById(id).orElseThrow(() -> new PolicyNotFoundException("Policy Id " + id + " does not exist to delete"));
		repo.deleteById(id);
		return message;

	}

}
