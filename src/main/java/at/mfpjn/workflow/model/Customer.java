package at.mfpjn.workflow.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getHasFacebook() {
		return hasFacebook;
	}

	public void setHasFacebook(Boolean hasFacebook) {
		this.hasFacebook = hasFacebook;
	}

	public Boolean getHasInstagram() {
		return hasInstagram;
	}

	public void setHasInstagram(Boolean hasInstagram) {
		this.hasInstagram = hasInstagram;
	}

	public Boolean getHasTwitter() {
		return hasTwitter;
	}

	public void setHasTwitter(Boolean hasTwitter) {
		this.hasTwitter = hasTwitter;
	}

	public Boolean getHasGoogle() {
		return hasGoogle;
	}

	public void setHasGoogle(Boolean hasGoogle) {
		this.hasGoogle = hasGoogle;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", admin=" + admin +
				", enabled=" + enabled +
				", hasFacebook=" + hasFacebook +
				", hasInstagram=" + hasInstagram +
				", hasTwitter=" + hasTwitter +
				", hasGoogle=" + hasGoogle +
				'}';
	}
}
