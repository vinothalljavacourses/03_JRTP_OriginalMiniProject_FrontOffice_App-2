package com.vinothit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vinothit.binding.DashboardResponseForm;
import com.vinothit.binding.EnquiryForm;
import com.vinothit.binding.EnquirySearchCriteriaForm;
import com.vinothit.entity.CourseEntity;
import com.vinothit.entity.EnquiryStatusEntity;
import com.vinothit.entity.StudentEnquiryEntity;
import com.vinothit.entity.UserDetailsEntity;
import com.vinothit.repository.CourseRepositoty;
import com.vinothit.repository.EnquiryStatusRepository;
import com.vinothit.repository.StudentEnquiryRepository;
import com.vinothit.repository.UserDetailsRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	
	@Autowired
	private StudentEnquiryRepository studentEnquiryRepository;
	
	@Autowired
	private CourseRepositoty courseRepositoty;
	
	@Autowired
	private EnquiryStatusRepository  enquiryStatusRepository;
	
	@Autowired
	private HttpSession session;
	

	@Override
	public DashboardResponseForm getDashboardResponse(Integer userId) {
		
		DashboardResponseForm dashboardResponseForm=new DashboardResponseForm();
       
		Optional<UserDetailsEntity> findById = userDetailsRepository.findById(userId);
		
		if(findById.isPresent()) {
			
			UserDetailsEntity userDetailsEntity = findById.get();
			
			List<StudentEnquiryEntity> enquiries = userDetailsEntity.getEnquiries();
			
			Integer totalCount = enquiries.size();
			
			List<StudentEnquiryEntity> enrolled = enquiries.stream()
			         .filter( e -> e.getEnquiryStatus().equals("Enrolled"))
			         .collect(Collectors.toList());
			
			Integer enrolledCount = enrolled.size();
			
			
			List<StudentEnquiryEntity> lost = enquiries.stream()
			         .filter( e -> e.getEnquiryStatus().equals("Lost"))
			         .collect(Collectors.toList());
			
			Integer lostCount = lost.size();
			
			dashboardResponseForm.setTotalEnquiries(totalCount);
			dashboardResponseForm.setEnrolledCount(enrolledCount);
			dashboardResponseForm.setLostCount(lostCount);
			dashboardResponseForm.setLoggedUserName(userDetailsEntity.getUserEmail());
			
			
		}
		
		return dashboardResponseForm;
	}

	@Override
	public boolean addEnquiry(EnquiryForm enquiryForm,Integer enquiryId) {
		
		StudentEnquiryEntity studentEnquiryEntity=new StudentEnquiryEntity();
		
		BeanUtils.copyProperties(enquiryForm, studentEnquiryEntity);
		
		studentEnquiryEntity.setPhoneNumber(enquiryForm.getStudentPhoneNumber());
		
		// get logged-in user id
		Integer userId = (Integer) session.getAttribute("userid");
		
		if(enquiryId != null) {
			studentEnquiryEntity.setEnquiryId(enquiryId);
		}
		
		// This is 1st Approach
		//UserDetailsEntity userDetailsEntity=new UserDetailsEntity();
		//userDetailsEntity.setUserId(userId);
		
		// This is 2nd Approach
		UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(userId).get();
		
		studentEnquiryEntity.setUser(userDetailsEntity);
		
        studentEnquiryRepository.save(studentEnquiryEntity);
        
        return true;
		
	}

	@Override
	public List<String> getAllCourses() {
		
		//List<String> allCourses = courseRepositoty.getAllCourses();
		
		List<CourseEntity> findAll = courseRepositoty.findAll();
		
		List<String> courseNames=new ArrayList<>();
		
		for(CourseEntity entity : findAll) {
			courseNames.add(entity.getCourseName());
		}
	
		return courseNames;
	}

	@Override
	public List<String> getAllEnquiryStatus() {
		
		//List<String> enquiryAllStatus = enquiryStatusRepository.getEnquiryAllStatus();
		
		List<EnquiryStatusEntity> findAll = enquiryStatusRepository.findAll();
		
		List<String> allEnquiryStatus=new ArrayList<>();
		
		for(EnquiryStatusEntity entity : findAll) {
			allEnquiryStatus.add(entity.getStatusName());
		}
		
		
		return allEnquiryStatus;
		
	}

	@Override
	public List<StudentEnquiryEntity> viewStudentEnquries() {
		
		// get logged-in user id
		Integer userId = (Integer) session.getAttribute("userid");
		
		UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(userId).get();
		
		StudentEnquiryEntity studentEnquiryEntity=new StudentEnquiryEntity();
		
        studentEnquiryEntity.setUser(userDetailsEntity);
        
        Example<StudentEnquiryEntity> studentEnquiryEntityFinal = Example.of(studentEnquiryEntity);
		
        List<StudentEnquiryEntity> finalEntity = studentEnquiryRepository.findAll(studentEnquiryEntityFinal);
        
       // System.out.println("finalEntity : " + finalEntity);
		//List<StudentEnquiryEntity> findAll = studentEnquiryRepository.findAll(
		
		return finalEntity;
	}

	@Override
	public boolean saveEnquriry(EnquiryForm EnquiryForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnquiryForm editEnquiry(Integer enquiryId) {
		
		Optional<StudentEnquiryEntity> studentEnquiryEntity = studentEnquiryRepository.findById(enquiryId);
		
		EnquiryForm enquiryForm=new EnquiryForm();
		
		if(studentEnquiryEntity.isPresent()) {
			
			StudentEnquiryEntity studentEnquiryEntityData = studentEnquiryEntity.get();
			BeanUtils.copyProperties(studentEnquiryEntityData, enquiryForm);
			enquiryForm.setStudentPhoneNumber(studentEnquiryEntityData.getPhoneNumber());
		}
		
		return enquiryForm;
	}

	

}
