package com.vinothit.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.vinothit.entity.CourseEntity;
import com.vinothit.entity.EnquiryStatusEntity;
import com.vinothit.repository.CourseRepositoty;
import com.vinothit.repository.EnquiryStatusRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CourseRepositoty courseRepositoty;
	
	@Autowired
	private EnquiryStatusRepository  enquiryStatusRepository;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		
		/*courseRepositoty.deleteAll();

		// Load Courses
		CourseEntity ce1 = new CourseEntity(1, "AWS");
		CourseEntity ce2 = new CourseEntity(2, "DevOps");
		CourseEntity ce3 = new CourseEntity(3, "Java");
		CourseEntity ce4 = new CourseEntity(4, "SpringBoot");

		List<CourseEntity> allCourses = Arrays.asList(ce1, ce2, ce3, ce4);

		List<CourseEntity> allSavedCourses = courseRepositoty.saveAll(allCourses);

		System.out.println("allSavedCourses :" + allSavedCourses);
		
		enquiryStatusRepository.deleteAll();
		
		// Load EnquiryStatus
		EnquiryStatusEntity ese1 = new EnquiryStatusEntity(1, "New");
		EnquiryStatusEntity ese2 = new EnquiryStatusEntity(2, "Enrolled");
		EnquiryStatusEntity ese3 = new EnquiryStatusEntity(3, "Lost");

		List<EnquiryStatusEntity> allEnquiryStatus = Arrays.asList(ese1, ese2, ese3);

		List<EnquiryStatusEntity> allSavedStatus = enquiryStatusRepository.saveAll(allEnquiryStatus);

		System.out.println("allSavedStatus :" + allSavedStatus);*/

	}
	
}
