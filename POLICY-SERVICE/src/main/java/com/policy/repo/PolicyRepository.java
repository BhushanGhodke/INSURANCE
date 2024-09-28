package com.policy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.entity.Policy;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	public Policy findByUserId(Integer userId);

	public Policy findByEmailAndMobile(String email, String mobile);

	
}
