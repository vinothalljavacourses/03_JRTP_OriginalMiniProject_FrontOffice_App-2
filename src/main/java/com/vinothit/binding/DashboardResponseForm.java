package com.vinothit.binding;

import lombok.Data;

@Data
public class DashboardResponseForm {

	private String loggedUserName;
	private Integer totalEnquiries;
	private Integer enrolledCount;
	private Integer LostCount;

}
