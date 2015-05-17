package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.Logging;

/**
 * Created by Peter on 17-May-15.
 */
public interface LoggingDao {

    void insertLog(Logging log);

    void updateLog(Logging Log);

    Logging getLog(int id);
}
