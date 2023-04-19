package com.vinothit.service;

import com.vinothit.binding.LoginForm;
import com.vinothit.binding.SignUpform;
import com.vinothit.binding.UnlockForm;

public interface UserService {
	
	public String login(LoginForm loginform);
	public String signup(SignUpform signUpform);
	public String unlock(UnlockForm unlockForm);
	public String forgotPassword(String emailId);

}
