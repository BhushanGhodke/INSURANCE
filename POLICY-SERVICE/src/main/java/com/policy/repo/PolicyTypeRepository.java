package com.policy.repo;

import com.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.entity.PolicyType;

public interface PolicyTypeRepository extends JpaRepository<PolicyType, Integer> {
    public PolicyType findByPlanId(Integer planId);
}
