
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
	StateService stateService;
	
	@Autowired
	CustomerAddressService customerAddressService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StateTranslationService stateTranslationService;
	
	
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
		List<State> states = stateService.getStates();
		List<StateTranslation> stateTranslations = stateTranslationService.getStates("en");
		City city = new City();
		CustomerAddress address = customerAddressService.getCustomerAddressByCustomerId(customer.getId());
		if (address!=null) {
			City temp = address.getCityFK();
			city = cityService.getCity(temp.getId());
			model.addAttribute("address",address);
			model.addAttribute("city",city);
		}
		model.addAttribute("states", states);
		model.addAttribute("stateTranslations", stateTranslations);
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
				updateAddress(request);
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
			sendConfirmationEmail(request, customer);
			return "customerCreated"; 
		}
	}
	
	public void updateAddress(HttpServletRequest request){
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = userDetails.getUsername();
		Customer customer = customerService.getUser(name);
		CustomerAddress address = customerAddressService.getCustomerAddressByCustomerId(customer.getId());
		String test = request.getParameter("address");		
		if (test != "") {  
			City testingCity = cityService.getCityByName(request.getParameter("city"));
			if (address == null) {
				CustomerAddress address1 = new CustomerAddress();
				address1.setAddress(request.getParameter("address"));
				address1.setAddress2(request.getParameter("address2"));
				address1.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
				if (testingCity == null) {
					City testingCity1 = new City();
					testingCity1.setName(request.getParameter("city"));
					State state = stateService.getState(Integer.parseInt(request.getParameter("states")));
					testingCity1.setStateFK(state); 
					cityService.insertCity(testingCity1);
					testingCity1 = cityService.getCityByName(request.getParameter("city"));
					address1.setCityFK(testingCity1);
				}else {
					address1.setCityFK(testingCity);
				}
				address1.setCustomerFK(customer);
				customerAddressService.insertCustomerAddress(address1);
			}else {
				address.setAddress(request.getParameter("address") );
				address.setAddress2(request.getParameter("address2") );
				address.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
				if (testingCity == null) {
					City testingCity1 = new City();
					testingCity1.setName(request.getParameter("city"));
					State state = stateService.getState(Integer.parseInt(request.getParameter("states")));
					testingCity1.setStateFK(state); 
					cityService.insertCity(testingCity1);
					testingCity1 = cityService.getCityByName(request.getParameter("city"));
					address.setCityFK(testingCity1);
				}else {
					address.setCityFK(testingCity);
				}							
				customerAddressService.updateCustomerAddress(address);
			}						
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
		customer.setTitle(request.getParameter("title"));
		if (request.getParameter("email") != customer.getEmail()) {
			usernameChanged = true;
		}
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(passwordEncoder.encode(request.getParameter("password")));
		customer.setLanguage(request.getParameter("language"));
		customer.setNewsletter(Boolean.parseBoolean(request.getParameter("newslatter")));
		customer.setPhone(null);
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
		//customer.setTitle(request.getParameter("title"));
		int test = Integer.parseInt(request.getParameter("title"));
		if (test == 0) {
			customer.setTitle("Mr.");
		}else {
			customer.setTitle("Ms.");
		}
		customer.setDob(request.getParameter("dob"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(passwordEncoder.encode(request.getParameter("password")));
		int test2 = Integer.parseInt(request.getParameter("language"));
		if (test2 == 0) {
			customer.setLanguage("en");
		}else {
			customer.setLanguage("de");
		}
		//customer.setLanguage(request.getParameter("language"));
		customer.setPictureLink(""); //nope...ovo ide kasnije
		customer.setAdmin(false);
		customer.setHonorPoints(0);
		customer.setPhone(null);
		customer.setNewsletter(Boolean.parseBoolean(request.getParameter("newslatter"))); 
		customer.setEnabled(true);
		
		customerService.insertCustomer(customer);
		//sendConfirmationEmail(request, customer);
	
	}
	
	public void addAuthority(HttpServletRequest request) {
		Authorities authority = new Authorities();
		authority.setUsername(request.getParameter("email"));
		authority.setAuthority("user");
		authoritiesService.insertAuthoritie(authority);
	}
}

