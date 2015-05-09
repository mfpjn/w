package at.mfpjn.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="authorities")
public class Authorities {

	@Id
	@Column(name="Username")
	String Username;
	
	@Column(name="Authority")
	String authority;

	public Authorities()
	{
		
	}
	
	public Authorities(String username, String authority) {
		super();
		Username = username;
		this.authority = authority;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authorities [Username=" + Username + ", authority=" + authority
				+ "]";
	}
	
	
	
}
