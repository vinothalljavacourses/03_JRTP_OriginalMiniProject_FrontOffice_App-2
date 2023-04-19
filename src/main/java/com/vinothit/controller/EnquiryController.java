package com.vinothit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {
	
	@GetMapping("/dashboard")
	public String dashloadPage() {
		return "dashboard";
	}

	@GetMapping("/addenquiry")
	public String addEnquiryPage() {
		return "add-enquiry";
	}
	
	@GetMapping("/viewenquires")
	public String viewEnquiryPage() {
		return "view-enquiries";
	}

}
