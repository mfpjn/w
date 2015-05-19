package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.UserMediaChannelsParameters;

/**
 * Created by Peter on 17-May-15.
 */
public interface UserMediaChannelsParametersDao {

    void insertParameter(UserMediaChannelsParameters parameter);

    void updateParameter(UserMediaChannelsParameters parameter);

    UserMediaChannelsParameters getParameter(int id);
}
