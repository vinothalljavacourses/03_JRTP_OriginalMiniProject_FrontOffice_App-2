package com.vinothit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "VIT_USER_DTLS")
public class UserDetailsEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="user_id")
	 private Integer userId;

	 private String userName;

	 private String userEmail;

	 private Long userPhoneNumber;

	 private String password;

	 private String accountStatus;
	 
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private List<StudentEnquiryEntity> enquiries;

}
