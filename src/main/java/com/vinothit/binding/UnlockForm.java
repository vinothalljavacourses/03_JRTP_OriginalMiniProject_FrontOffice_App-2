package com.vinothit.binding;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UnlockForm {

	private String userEmail;
	@NotNull(message = "Temporary Password is mandatory")
	private String temporaryPassword;
	@NotNull(message = "New Password is mandatory")
	private String newPassword;
	@NotNull(message = "Confirm Password is mandatory")
	private String confirmPassword;
}
