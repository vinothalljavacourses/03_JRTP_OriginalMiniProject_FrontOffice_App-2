package com.vinothit.service;

import java.util.List;

import com.vinothit.binding.DashboardResponseForm;
import com.vinothit.binding.EnquiryForm;
import com.vinothit.binding.EnquirySearchCriteriaForm;

public interface EnquiryService {
	
	public DashboardResponseForm dashboardResponse();
	public String addEnquiry(EnquiryForm enquiryForm);
	public List<String>  getCourses();
	public List<String>  getEnquiryStatus();
	public List<DashboardResponseForm> viewStudentEnquries(String userId, EnquirySearchCriteriaForm enquirySearchCriteriaForm);

}
