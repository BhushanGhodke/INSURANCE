package com.policy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	public Policy findByUserId(Integer userId);

	
}
