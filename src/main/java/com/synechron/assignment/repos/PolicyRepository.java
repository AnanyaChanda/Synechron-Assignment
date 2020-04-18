package com.synechron.assignment.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.assignment.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

}
