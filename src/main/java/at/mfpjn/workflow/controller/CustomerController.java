
package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.model.*;
import at.mfpjn.workflow.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	SendMail sendMail;	
	
	@Autowired
	UserMediaChannelsParametersService umcpService;
	
	
	@RequestMapping("/registerCustomer")
	public String showNewAccount(Model model) {
		model.addAttribute("customer", new Customer());
		return "registerCustomer";
	}
	
	@RequestMapping(value="/editCustomerInformation", method= RequestMethod.GET)
	public String updateInfo(Model model) {
		//org.springframework.security.core.userdetails.User current = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = userDetails.getUsername();
		Customer customer = customerService.getUser(name);
		customer.setPassword("");							
		model.addAttribute("customer", customer);
		return "editCustomerInformation";
	}
	
	@RequestMapping(value="/resetPassword")
	public String resetPassword(Model model, Customer customer){
		model.addAttribute("customer", new Customer());
		return "resetPassword";
	}
	
	@RequestMapping(value="/sendTempPass")
	public String sendTempPass(Model model,Customer customer,BindingResult result,HttpServletRequest request){
		Customer customer1 = customerService.getUser(request.getParameter("email"));
		if (customer1 == null) {
			boolean error = true;
			model.addAttribute("error", error);						
			return "resetPassword"; 
		}else {
			int passLenght = 8;
			Random random = new SecureRandom();
			String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
			
			String pass = "";
			for (int i = 0; i < passLenght; i++) {
				int index = (int)(random.nextDouble()*letters.length());
				pass += letters.substring(index, index+1);
			}
			SimpleMailMessage email = new SimpleMailMessage();
			String emailText = String.format("Dear Mr./Ms. %s, \n\nYou have requested a password change. Your new password is: " + pass, customer1.getLastName());
			email.setTo(customer1.getEmail());
			email.setSubject("Workflow password reset");
			email.setText(emailText);
			mailSender.send(email);
			customer1.setPassword(passwordEncoder.encode(pass));
			customerService.updateUser(customer1);
			return "passwordReseted";
		}
	}
	
	@RequestMapping(value="/updateCustomer", method = RequestMethod.POST)
	public String updateUser(Model model,@Valid Customer customer, BindingResult result, HttpServletRequest request){
		if (result.hasErrors()) {
			return "editCustomerInformation";
		} else {
			try {
				updateUserInfo(request);				
			} catch (Exception e) {
				e.printStackTrace();
				result.rejectValue("email", "DuplicateUsername.customer.email", "This email already exists!");
				return "editCustomerInformation";
			}
		}
		return "customerUpdated";
	}
	
	@RequestMapping(value="sendEmail")
	public String TestingSendingEmail(){
		SendMail sm = new SendMail();
		sm.sendMail("andatu7@gmail.com", "fujin6@gmail.com", "Email test", "Čvaljio si..");
		return "home";
	}
	
	@RequestMapping(value="/createCustomer", method = RequestMethod.POST)
	public String createNewCustomer(Model model, @Valid Customer customer,BindingResult result,HttpServletRequest request){
		if (result.hasErrors()) {
			return "registerCustomer"; 
		}else {
			try {
				addCustomer(request);
			} catch (Exception e) {
				result.rejectValue("email", "DuplicateUsername.customer.email", "This e-mail already exists!");
				e.printStackTrace();
				return "registerCustomer"; 
			}
			model.addAttribute("authoritie", new Authorities());
			addAuthority(request);
			addUserMediaChannels(request);
			sendConfirmationEmail(request, customer);
			return "customerCreated"; 
		}
	}
		
	
	public void updateUserInfo(HttpServletRequest request){
		//org.springframework.security.core.userdetails.User current = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String name = current.getUsername();
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = userDetails.getUsername();
		Customer customer = customerService.getUser(name);
		boolean usernameChanged = false;
		String oldUsername = "";
		oldUsername = customer.getEmail();
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));		
		if (request.getParameter("email") != customer.getEmail()) {
			usernameChanged = true;
		}
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(passwordEncoder.encode(request.getParameter("password")));		
		customerService.updateUser(customer);
		
		if (usernameChanged == true) {
			Authorities autorities = authoritiesService.getByUsername(oldUsername);
			autorities.setUsername(request.getParameter("email"));
			authoritiesService.updateAuthority(autorities);
		}
	}
	
	public void sendConfirmationEmail(HttpServletRequest request, Customer customer)
	{
		SimpleMailMessage email = new SimpleMailMessage();
		String emailText = String.format("Dear Mr./Ms. %s, \n\nThank you for registering to Workflow online service. Your account is now ready to use.", customer.getLastName());
		email.setTo(customer.getEmail());
		email.setSubject("Workflow registration");
		email.setText(emailText);
		mailSender.send(email);
	}
	
	public void addCustomer(HttpServletRequest request) {		
		Customer customer = new Customer();
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));		
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(passwordEncoder.encode(request.getParameter("password")));		
		customer.setAdmin(false);		
		customer.setEnabled(true);
		int test = Integer.parseInt(request.getParameter("facebook"));
		if (test == 1) {
			customer.setHasFacebook(true);
		}else {
			customer.setHasFacebook(false);
		}
		int test2 = Integer.parseInt(request.getParameter("twitter"));
		if (test2 == 1) {
			customer.setHasTwitter(true);
		}else {
			customer.setHasTwitter(false);
		}
		customer.setHasGoogle(false);
		customer.setHasInstagram(false);
		customerService.insertCustomer(customer);		
	
	}
	
	public void addAuthority(HttpServletRequest request) {
		Authorities authority = new Authorities();
		authority.setUsername(request.getParameter("email"));
		authority.setAuthority("user");
		authoritiesService.insertAuthoritie(authority);
	}
	
	public void addUserMediaChannels(HttpServletRequest request){
		UserMediaChannelsParameters media = new UserMediaChannelsParameters();
		Customer customer = customerService.getUser(request.getParameter("email"));
		int test = Integer.parseInt(request.getParameter("facebook"));
		if (test == 1) {
			media.setAccessToken(request.getParameter("accesstoken"));
			media.setAccessTokenSecret(request.getParameter("accesstokensecret"));
			media.setMedia("Facebook");	
			media.setCustomerId(customer);			
			umcpService.insertParameter(media);
		}
		int test2 = Integer.parseInt(request.getParameter("twitter"));
		if (test2 == 1) {
			media.setAccessToken(request.getParameter("accesstokenT"));
			media.setAccessTokenSecret(request.getParameter("accesstokensecretT"));
			media.setMedia("Twitter");
			media.setCustomerId(customer);
			
			umcpService.insertParameter(media);
		}
	}
}

