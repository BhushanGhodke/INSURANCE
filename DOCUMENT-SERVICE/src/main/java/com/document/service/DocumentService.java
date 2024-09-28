package com.document.service;

import jakarta.servlet.http.HttpServletResponse;

public interface DocumentService {

	public void generatePdf(HttpServletResponse response,Integer policyId)throws Exception;
}
