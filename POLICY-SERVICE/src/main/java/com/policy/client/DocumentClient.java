package com.policy.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "DOCUMENT-SERVICE")
public interface DocumentClient {

    @GetMapping("/document/pdf/{policyId}")
    public Boolean PreparePdfDocumetForPolciy(@PathVariable Integer policyId);
}
