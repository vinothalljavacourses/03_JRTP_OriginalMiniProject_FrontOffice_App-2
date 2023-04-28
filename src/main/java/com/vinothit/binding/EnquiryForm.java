package com.vinothit.binding;

import lombok.Data;

@Data
public class EnquiryForm {

	private Integer enquiryId;
	private String studentName;
	private Long studentPhoneNumber;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
}
