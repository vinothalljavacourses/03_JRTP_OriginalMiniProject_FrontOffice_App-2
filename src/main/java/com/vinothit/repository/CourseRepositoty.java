package com.vinothit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinothit.entity.CourseEntity;

public interface CourseRepositoty extends JpaRepository<CourseEntity, Integer> {
	
	@Query("select courseName from CourseEntity")
	public List<String> getAllCourses();
	


}
