package com.synechron.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Policy")
public class Policy {
	
	@Id
	private int policyId;
	private String policyName;
	private int premiunPerMonth;
	private double coveragePerClosure;

	
	
	

}
