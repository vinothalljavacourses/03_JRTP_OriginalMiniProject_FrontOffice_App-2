package com.vinothit.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.vinothit.binding.DashboardResponseForm;
import com.vinothit.binding.EnquiryForm;
import com.vinothit.binding.EnquirySearchCriteriaForm;
import com.vinothit.entity.StudentEnquiryEntity;

public interface EnquiryService {
	
	public DashboardResponseForm getDashboardResponse(Integer userId);
	public boolean addEnquiry(EnquiryForm enquiryForm,Integer enquiryId);
	public List<String>  getAllCourses();
	public List<String>  getAllEnquiryStatus();
	public boolean saveEnquriry(EnquiryForm EnquiryForm);
	public List<StudentEnquiryEntity> viewStudentEnquries();
	public EnquiryForm editEnquiry(Integer enquiryId);

}
