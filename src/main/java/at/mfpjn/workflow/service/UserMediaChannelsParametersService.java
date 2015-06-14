package at.mfpjn.workflow.service;

/**
 * Created by Peter on 17-May-15.
 */

import at.mfpjn.workflow.dao.UserMediaChannelsParametersDao;
import at.mfpjn.workflow.model.UserMediaChannelsParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userMediaChannelsParametersService")
public class UserMediaChannelsParametersService {

    @Autowired
    private UserMediaChannelsParametersDao userMediaChannelsParametersDao;

    public void insertParameter(UserMediaChannelsParameters parameter) {
        userMediaChannelsParametersDao.insertParameter(parameter);
    }

    public void updateParameter(UserMediaChannelsParameters parameter){
        userMediaChannelsParametersDao.updateParameter(parameter);
    }

    public UserMediaChannelsParameters getParameter(int id) {
        return userMediaChannelsParametersDao.getParameter(id);
    }
    
    public UserMediaChannelsParameters getTwitterParameter(int id) {
        return userMediaChannelsParametersDao.getTwitterParameter(id);
    }
    
    public UserMediaChannelsParameters getFacebookParameter(int id) {
        return userMediaChannelsParametersDao.getFacebookParameter(id);
    }

	public void deleteTwitterParameters(int id) {
		userMediaChannelsParametersDao.deleteTwitterParameters(id);
	}
}
