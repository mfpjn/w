package at.mfpjn.workflow.service;

import at.mfpjn.workflow.dao.AuthoritiesDao;
import at.mfpjn.workflow.model.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthoritiesService {

	@Autowired
	private AuthoritiesDao authoritiesDao;
	
	public void insertAuthoritie(Authorities authority){
		authoritiesDao.insertAuthority(authority);
	}
	
	public void updateAuthority(Authorities authority){
		authoritiesDao.updateAuthority(authority);
	}
	
	public Authorities getByUsername(String username){
		return authoritiesDao.getByUsername(username);
	}
	
}
