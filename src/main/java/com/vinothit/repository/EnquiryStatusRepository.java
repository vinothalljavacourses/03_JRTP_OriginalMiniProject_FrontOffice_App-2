package com.vinothit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinothit.entity.EnquiryStatusEntity;

public interface EnquiryStatusRepository extends JpaRepository<EnquiryStatusEntity, Integer> {
	
	@Query("select statusName from EnquiryStatusEntity")
	public List<String> getEnquiryAllStatus();

}
