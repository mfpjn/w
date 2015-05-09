
package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.model.Authorities;
import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.service.AuthoritiesService;
import at.mfpjn.workflow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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


    @RequestMapping("/registerCustomer")
    public String showNewAccount(Model model) {
        model.addAttribute("customer", new Customer());
        return "registerCustomer";
    }

    @RequestMapping("/editCustomerInformation")
    public String updateInfo(Model model) {
        org.springframework.security.core.userdetails.User current = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = current.getUsername();
        Customer customer = customerService.getUser(name);
        customer.setPassword("");
        model.addAttribute("customer", customer);
        return "editCustomerInformation";
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateUser(Model model, @Valid Customer customer, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "editCustomerInformation";
        } else {
            try {
                updateUserInfo(request);//
            } catch (Exception e) {
                e.printStackTrace();
                result.rejectValue("email", "DuplicateUsername.customer.email", "This email already exists!");
                return "editCustomerInformation";
            }
        }
        return "customerUpdated";
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String createNewCustomer(Model model, @Valid Customer customer, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "registerCustomer";
        } else {
            try {
                addCustomer(request);
            } catch (Exception e) {
                result.rejectValue("email", "DuplicateUsername.customer.email", "This e-mail already exists!");
                e.printStackTrace();
                return "registerCustomer";
            }
            model.addAttribute("authoritie", new Authorities());
            addAuthority(request);

            return "customerCreated";
        }
    }

    public void updateUserInfo(HttpServletRequest request) {
        org.springframework.security.core.userdetails.User current = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = current.getUsername();
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
        customerService.updateUser(customer);

        if (usernameChanged == true) {
            Authorities autorities = authoritiesService.getByUsername(oldUsername);
            autorities.setUsername(request.getParameter("email"));
            authoritiesService.updateAuthority(autorities);
        }
    }

    public void sendConfirmationEmail(HttpServletRequest request, Customer customer) {
        SimpleMailMessage email = new SimpleMailMessage();
        //String emailText = String.format("Dear Mr./Ms. %s, \n\nThank you for registering to Andatu online service. Your account is now ready to use.", customer.getName());
        email.setTo(customer.getEmail());
        email.setSubject("Andatu registration");
        //email.setText(emailText);
        mailSender.send(email);
    }

    public void addCustomer(HttpServletRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setTitle(request.getParameter("title"));
        customer.setDob(dateFormat.format(date));
        customer.setEmail(request.getParameter("email"));
        customer.setPassword(passwordEncoder.encode(request.getParameter("password")));
        customer.setLanguage(request.getParameter("language"));
        customer.setPictureLink(""); //nope...ovo ide kasnije
        customer.setAdmin(false);
        customer.setHonorPoints(0);
        customer.setPhone(request.getParameter("phone"));
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

