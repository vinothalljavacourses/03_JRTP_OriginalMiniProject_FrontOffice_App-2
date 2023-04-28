package com.vinothit.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {

	@NotBlank
	private String userName;
	@NotBlank
	private String userPassword;

}
