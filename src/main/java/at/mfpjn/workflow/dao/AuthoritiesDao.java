package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.Authorities;


public interface AuthoritiesDao {

	void insertAuthority(Authorities authority);
	
	void updateAuthority(Authorities authority);
	
	Authorities getByUsername(String username);
}
