package com.document.service;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.document.client.PolicyClient;
import com.document.entity.Policy;
import com.document.entity.PolicyType;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class DocumerServiceImpl implements DocumentService {

	@Autowired
	private PolicyClient policyClient;

	@Override
	public void generatePdf(HttpServletResponse response, Integer policyId) throws Exception {

		Policy policy = policyClient.getPolicyById(policyId);
		
	    System.out.println( policy.toString());
		Document pdfDoc = new Document (PageSize.A4); 
		//ByteArrayOutputStream out = new ByteArrayOutputStream();
		ServletOutputStream outputStream = response.getOutputStream(); 
		PdfWriter.getInstance(pdfDoc, outputStream);
		pdfDoc.open();
		
		pdfDoc.setHtmlStyleClass("border");
		
		Paragraph p= new Paragraph("POLICY DETAILS");
		p.setAlignment("center");
		Font font= new Font();
		font.setColor(Color.BLUE);
		p.setFont(font);
		pdfDoc.add(p);
		
		
		Paragraph name=new Paragraph("Policy Holder Name : ");
		name.add(policy.getFirstname());
		name.add(" ");
		name.add(policy.getLastname());
		pdfDoc.add(name);
		
		
		Paragraph email = new Paragraph("Email Address : ");
		email.add(policy.getEmail());
		pdfDoc.add(email);
		
		Paragraph mobile = new Paragraph("Mobile No.  : ");
		mobile.add(policy.getMobile());
		pdfDoc.add(mobile);
		
		Paragraph address = new Paragraph("Resident Address : ");
		address.add(policy.getAddress());
		address.add(" ");
		address.add(policy.getPinCode().toString());
		pdfDoc.add(address);
		
		Paragraph policyName = new Paragraph("Policy Name : ");
	     policyName.add(policy.getPlanType());
		pdfDoc.add(policyName);
		
		
		Paragraph premium = new Paragraph("Premium Amount : ");
		premium.add("Rs. "+ policy.getPremiumAmount().toString() +"           ");
		premium.add("Payment Status : ");
		premium.add(policy.getPaymentStatus());
		pdfDoc.add(premium);
		
		
		Paragraph date1= new Paragraph("Policy Period : ");
		date1.add("Start Date : ");
		date1.add(policy.getStartDate().toString()+"       ");
		date1.add("End Date : ");
		date1.add(policy.getEndDate().toString());
		pdfDoc.add(date1);
		
		
		pdfDoc.close();
	}

}
