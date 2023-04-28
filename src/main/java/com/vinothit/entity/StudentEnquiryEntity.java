package com.vinothit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "VIT_STUDENT_ENQURIES")
public class StudentEnquiryEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer enquiryId; 

	 private String studentName; 		

	 private Long phoneNumber; 

	 private String classMode;  		

	 private String courseName; 		

	 private String enquiryStatus; 		

	 @Column(name = "created_date", nullable = false, updatable = false)
	 @CreationTimestamp
	 private LocalDate createdDate; 			

	 @Column(name = "updated_date", nullable = false, updatable = true)
	 @UpdateTimestamp
	 private LocalDate updatedDate; 
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private UserDetailsEntity user;
			
}
