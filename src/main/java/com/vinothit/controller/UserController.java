package com.vinothit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinothit.binding.DashboardResponseForm;
import com.vinothit.binding.LoginForm;
import com.vinothit.binding.SignUpform;
import com.vinothit.binding.UnlockForm;
import com.vinothit.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private Validator validator;
     
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpform());
		return "signup";
	}

	@PostMapping("/signup")
	public String signUpPage(@ModelAttribute("user") SignUpform signUpform, Model model) {

		boolean signupStatus = userService.signup(signUpform);

		if (signupStatus) {
			model.addAttribute("successMessage", "Account Created, Please check Your Email");

		} else {
			model.addAttribute("errorMessage", "Choose Unique Email");
		}
		return "signup";
	}

	@GetMapping("/unlock")
	public String unlockPage(Model model, @RequestParam("email") String email) {
		
        //model.addAttribute("emailuser", email);
		UnlockForm unlockForm=new UnlockForm();
		unlockForm.setUserEmail(email);
		model.addAttribute("unlockuser", unlockForm);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@Validated @ModelAttribute("unlockuser") UnlockForm unlockForm, 
			                       Model model, BindingResult result) {
		
		if (unlockForm.getNewPassword().equals(unlockForm.getConfirmPassword())) {
			
			boolean unlockAccountStatus = userService.unlockAccount(unlockForm);
			
			if (unlockAccountStatus) {
				model.addAttribute("successMessage", "Your account unlocked successsfully");
			} else {
				model.addAttribute("errorMessage", "Given Temporary Password is incorrect, check your email");
			}
		} else {
			model.addAttribute("errorMessage", "NewPassword and ConfirmPassword should be same");

		}

		return "unlock";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("user") LoginForm loginForm, Model model, HttpSession session) {

        if(loginForm.getUserName().equals("")) {
        	model.addAttribute("errorMessage", "UserName filed should not be blank");
        	return "login";
        }
        
        if(loginForm.getUserPassword().equals("")) {
        	model.addAttribute("errorMessage", "Password filed should not be blank");
        	return "login";
        }
		
		String loginStatus = userService.login(loginForm);
		
		if(loginStatus.contains("success")){
			return "redirect:/dashboard";
		}

		model.addAttribute("errorMessage", loginStatus);

		return "login";

	}
	
	@GetMapping("/forgot")
	public String forgotPasswordPage(Model model) {
		
		System.out.println("UserController @GetMapping :: forgotPasswordPage");
        //model.addAttribute("user", new SignUpform());

		return "forgotPwd";
	}
	
	@PostMapping("/forgot")
	public String forgotPasswordPage(@RequestParam("email") String email, Model model) {
		

		boolean forgotPassword = userService.forgotPassword(email);
		
		if(forgotPassword) {
			model.addAttribute("checkEmailSuccess", "Your Password has just sent it to your registered email. Please check and use that password to login");
		}else {
			model.addAttribute("checkEmailFailure","Your email is not registered with us. So please signup with your email account for registerting");
		}

		return "forgotPwd";
	}
	
	
	
	@GetMapping("/signout")
	public String signOut(HttpSession session) {
		String beforeSessionInvalidate = (String)session.getAttribute("sessionusername");
		System.out.println("beforeSessionInvalidate : " + beforeSessionInvalidate);
		// make session invalidate while logging out 
		session.invalidate();
		return "redirect:/";
	}

}
