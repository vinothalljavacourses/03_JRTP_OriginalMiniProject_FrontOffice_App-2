package com.vinothit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinothit.binding.DashboardResponseForm;
import com.vinothit.binding.EnquiryForm;
import com.vinothit.binding.LoginForm;
import com.vinothit.entity.StudentEnquiryEntity;
import com.vinothit.service.EnquiryService;
import com.vinothit.service.UserService;
import org.springframework.ui.Model;

@Controller
public class EnquiryController {

	@Autowired
	private UserService userService;

	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	private HttpSession session;
	

	@GetMapping("/dashboard")
	public String dashloadPage(Model model) {
		Integer loggedInUser = (Integer) session.getAttribute("userid");
		
		DashboardResponseForm dashboardUserData = enquiryService.getDashboardResponse(loggedInUser);
		
		if(dashboardUserData != null) {
			
			model.addAttribute("dashboardUserData", dashboardUserData);
			
		}else {
			
		}
	
		return "dashboard";
	}
	

	@GetMapping("/addenquiry")
	public String addEnquiryPage(Model model) {

		// TO-DO 0: set empty EnquiryForm form
		model.addAttribute("stundentenquiry", new EnquiryForm());
		
		// TO-DO 1 - get all Courses for courses select box dripdown
		List<String> courses = enquiryService.getAllCourses();
		model.addAttribute("courses", courses);
		
		// TO-DO 2 - get all Enquiry Status for Enquiry Status select box dropdown
		List<String> enquiryStatus = enquiryService.getAllEnquiryStatus();
		model.addAttribute("enquiryStatus", enquiryStatus);
		
		return "add-enquiry";
	}

	@PostMapping("/addenquiry")
	public String addEnquiryPage(@ModelAttribute("stundentenquiry") EnquiryForm equiryForm, Model model,@RequestParam("enquiryId") Integer enquiryId) {

		System.out.println("equiryForm :: " + equiryForm);
		
		System.out.println("enquiryId :: " + enquiryId);
		
		 // TO-DO Save the data
		boolean savedStatus = enquiryService.addEnquiry(equiryForm,enquiryId);
	
		if (savedStatus) {
			model.addAttribute("successMessage", "Stundent Enquiry is successfully added into DB");
		} else {
			model.addAttribute("failureMessage", "Problem Occured");
		}

		return "add-enquiry";
	}

	@GetMapping("/viewenquires")
	public String viewEnquiryPage(Model model) {
		
		// TO-DO 0: set empty EnquiryForm form
		model.addAttribute("stundentenquiry", new EnquiryForm());
		
		// TO-DO 1 - get all Courses for courses select box dripdown
		List<String> courses = enquiryService.getAllCourses();
		model.addAttribute("courses", courses);

		// TO-DO 2 - get all Enquiry Status for Enquiry Status select box dropdown
		List<String> enquiryStatus = enquiryService.getAllEnquiryStatus();
		model.addAttribute("enquiryStatus", enquiryStatus);
				
		List<StudentEnquiryEntity> viewStudentEnquries = enquiryService.viewStudentEnquries();
		model.addAttribute("viewAllEnquiries", viewStudentEnquries);
		return "view-enquiries";
	}
	

	@GetMapping("/editenquiry")
	public String editEnquiry(@RequestParam("enquiryId") Integer enquiryId, Model model) {
		
		// TO-DO 1 - get all Courses for courses select box drop down
		List<String> courses = enquiryService.getAllCourses();
		model.addAttribute("courses", courses);

		// TO-DO 2 - get all Enquire Status for Inquiry Status select box drop down
		List<String> enquiryStatus = enquiryService.getAllEnquiryStatus();
		model.addAttribute("enquiryStatus", enquiryStatus);
				
		System.out.println("enquiryId : " + enquiryId);
		
		EnquiryForm editEnquiry = enquiryService.editEnquiry(enquiryId);
		editEnquiry.setEnquiryId(enquiryId);
		
		model.addAttribute("stundentenquiry", editEnquiry);
		
		model.addAttribute("enquiryId", enquiryId);
		
		return "add-enquiry";
	}
	

}
