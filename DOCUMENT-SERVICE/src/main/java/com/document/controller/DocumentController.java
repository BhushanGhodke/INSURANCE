package com.document.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.document.service.DocumentService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/document")
@CrossOrigin
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@GetMapping("/pdf/{policyId}")
	public Boolean preparePdfFileForPolicy(@PathVariable Integer policyId) {

		return true;
	}

	@GetMapping("/pdf1/{policyId}")
	public void downloadPdf(HttpServletResponse response, @PathVariable Integer policyId) throws Exception {

		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Policy.pdf";
		response.addHeader(headerKey, headerValue);

		documentService.generatePdf(response, policyId);
	}

}
