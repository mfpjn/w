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
	
	@Column(name="Title")
	private String title;
	
	@Column(name="DOB")
	private String dob;

	@Column(name="Email")
	@Email(message="This does not appear to be a valid e-mail address!")
	@NotBlank(message="E-mail can not be blank!")
	private String email;
	
	@Column(name="Password")
	@Size(min=5, max=100, message="Password must be between 5 and 50 characters!")
	@NotBlank(message="Password cannot be blank!")
	@Pattern(regexp="^[a-zA-Z0-9]\\w{5,100}$", message="This is not a valid password")
	private String password;
	
	@Column(name="Language")
	@NotBlank(message="Language can not be blank!")
	private String language;
	
	@Column(name="PictureLink")
	private String pictureLink;
	
	@Column(name="Admin")
	private Boolean admin;
	
	@Column(name="HonorPoints")
	private int honorPoints;
	
	@Column(name="Phone")
	@Size(min=10, message="Size ")
	@NotBlank(message="Phone number can not be blank!")
	private String phone;
	
	@Column(name="NewsLetter")
	private Boolean newsletter;
	
	@Column(name="enabled")
	private Boolean enabled;

	public Customer()
	{
		
	}

	
	public Customer(int id, String firstName, String lastName, String dob, String email,String title,
			String password, String language, String pictureLink,
			Boolean admin, int honorPoints, String phone, Boolean newsletter,
			Boolean enabled) {

		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.language = language;
		this.pictureLink = pictureLink;
		this.admin = admin;
		this.honorPoints = honorPoints;
		this.phone = phone;
		this.newsletter = newsletter;
		this.enabled = enabled;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public int getHonorPoints() {
		return honorPoints;
	}

	public void setHonorPoints(int honorPoints) {
		this.honorPoints = honorPoints;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}

	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName

				+ ", lastName=" + lastName + ", title=" + title + ", dob="
				+ dob + ", email=" + email + ", password=" + password
				+ ", language=" + language + ", pictureLink=" + pictureLink
				+ ", admin=" + admin + ", honorPoints=" + honorPoints
				+ ", phone=" + phone + ", newsletter=" + newsletter
				+ ", enabled=" + enabled + "]";
	}

}
