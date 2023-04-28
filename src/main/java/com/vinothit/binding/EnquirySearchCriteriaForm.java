package com.vinothit.binding;

import java.util.List;

import lombok.Data;

@Data
public class EnquirySearchCriteriaForm {

	private String course;
	private String enquiryStatus;
	private String classMode;
	private List<EnquiryForm> listOfEnquiries;
}
