package com.vinothit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinothit.binding.LoginForm;
import com.vinothit.binding.SignUpform;
import com.vinothit.binding.UnlockForm;
import com.vinothit.entity.UserDetailsEntity;
import com.vinothit.repository.UserDetailsRepository;
import com.vinothit.utils.EmailUtils;
import com.vinothit.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean signup(SignUpform signUpform) {
		
		// TO-DO 1: Check whether current passing email id is already existing or not
		if (signUpform.getUserEmail() != null) {
			UserDetailsEntity userExsisting = userDetailsRepository.findByUserEmail(signUpform.getUserEmail());
			if (userExsisting != null) {
				return false;
			}
		}

		// TO-DO 2: Copy data from binding obj to entity obj
		UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
		BeanUtils.copyProperties(signUpform, userDetailsEntity);

		// TO-DO 3: Generate random password and set to Object
		String generateRandomPassword = PasswordUtils.generateRandomPassword();
		userDetailsEntity.setPassword(generateRandomPassword);

		// TO-DO 4: Set account status as LOCKED
		userDetailsEntity.setAccountStatus("LOCKED");

		Boolean isUserEmailAlreadyExsisting = false;

		// TO-DO 5: Insert record into MySQL DB
		userDetailsRepository.save(userDetailsEntity);

		// TO-DO 6: Send email to user to unlock the account
		String subject = "Unlock Your Account | Vinoth IT";
		String toEmail = signUpform.getUserEmail();
		
	     StringBuffer body=new StringBuffer();
	     body.append("<h1> Use below temporary password to unlock your account</h1>");
	     body.append("Temporary password : " + generateRandomPassword);
	     body.append("<br/>");
	     body.append("<a href=\"http://localhost:8080/unlock?email="+ toEmail +"\"> Click Here To Unlock Your Account ");


		// call sendEmail function to send email to user for unlock
		boolean sendEmail = emailUtils.sendEmail(subject, body.toString(), toEmail);

		if (sendEmail) {
			return true;
		}

		return false;
	}

	@Override
	public boolean unlockAccount(UnlockForm unlockForm) {

		// TO-DO 1 : check user passing email and their temp password is matching with DB password
		UserDetailsEntity userDetailsEntity = userDetailsRepository.findByUserEmail(unlockForm.getUserEmail());
		
			if(userDetailsEntity.getPassword().equals(unlockForm.getTemporaryPassword())) {
			  	  userDetailsEntity.setPassword(unlockForm.getNewPassword());
			  	  userDetailsEntity.setAccountStatus("UNLOCKED");
			  	  userDetailsRepository.save(userDetailsEntity);
			  	  return true;
				
			}else {
				return false;
			}

	}
	
	@Override
	public String login(LoginForm loginform) {

		// TO-DO 1 : Check whether this is userIs and password are existing in DB or Not
		UserDetailsEntity findByUserEmailPassword = userDetailsRepository
				.findByUserEmailAndPassword(loginform.getUserName(), loginform.getUserPassword());

		// TO-DO 2 : If not, then send the message as "Invalid Credentials"
		if (findByUserEmailPassword == null) {
			return "Invalid Credentials";
		}

		// TO-DO 3 : Check this user is in UNLOCKED status and then only allow to login
		if (findByUserEmailPassword.getAccountStatus().equals("LOCKED")) {
			return "Your Account is Locked";
		}
		
		// TO-DO 4 : put this UserId in session object for inquiry screen purpose
		session.setAttribute("userid", findByUserEmailPassword.getUserId());

		return "success";
	}

	@Override
	public boolean forgotPassword(String email) {
		
		String userPassword = null;
		
		UserDetailsEntity userDetailsEntity = userDetailsRepository.findByUserEmail(email);
		
		if(userDetailsEntity == null) {
			return false;
		}

		if (userDetailsEntity != null) {
			if (userDetailsEntity.getAccountStatus().equals("UNLOCKED")) {
				userPassword = userDetailsEntity.getPassword();

				// TO-DO 6: Send email to user to unlock the account
				String subject = "Your Account Info | Vinoth IT";

				StringBuffer body = new StringBuffer();
				body.append("<h1> Use below password to login your account</h1>");
				body.append("Your Password : " + userPassword);
				body.append("<br/>");
				body.append("<a href=\"http://localhost:8080/login" 
						+ "\"> Click Here To Login Your Account ");

				// call sendEmail function to send email to user for unlock
				emailUtils.sendEmail(subject, body.toString(), email);

			}
	
		}
		
		return true;
		
	}

	



}
