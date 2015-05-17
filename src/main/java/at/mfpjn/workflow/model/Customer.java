package at.mfpjn.workflow.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	
	@Column(name="FirstName")
	@NotBlank(message="First name cannot be blank!")
	@Pattern(regexp="^[-a-zA-Z]+$", message="First name can only consist of letters!")
	private String firstName;
	
	@Column(name="LastName")
	@NotBlank(message="Last name cannot be blank!")
	@Pattern(regexp="^[-a-zA-Z]+$", message="Last name can only consist of letters!")
	private String lastName;

	@Column(name="Email")
	@Email(message="This does not appear to be a valid e-mail address!")
	@NotBlank(message="E-mail can not be blank!")
	private String email;
	
	@Column(name="Password")
	@Size(min=5, max=100, message="Password must be between 5 and 50 characters!")
	@NotBlank(message="Password cannot be blank!")
	@Pattern(regexp="^[a-zA-Z0-9]\\w{5,100}$", message="This is not a valid password")
	private String password;
	
	@Column(name="Admin", columnDefinition = "TINYINT(1)")
	private Boolean admin;

	@Column(name="Enabled", columnDefinition = "TINYINT(1)")
	private Boolean enabled;

	@Column(name="HasFacebook", columnDefinition = "TINYINT(1)")
	private Boolean hasFacebook;

	@Column(name="HasInstagram", columnDefinition = "TINYINT(1)")
	private Boolean hasInstagram;

	@Column(name="HasTwitter", columnDefinition = "TINYINT(1)")
	private Boolean hasTwitter;

	@Column(name="HasGoogle", columnDefinition = "TINYINT(1)")
	private Boolean hasGoogle;

	public Customer()
	{
	}


}
