package com.vinothit.service;

import com.vinothit.binding.LoginForm;
import com.vinothit.binding.SignUpform;
import com.vinothit.binding.UnlockForm;

public interface UserService {
	
	public String login(LoginForm loginform);
	public boolean signup(SignUpform signUpform);
	public boolean unlockAccount(UnlockForm unlockForm);
	public boolean forgotPassword(String email);

}
