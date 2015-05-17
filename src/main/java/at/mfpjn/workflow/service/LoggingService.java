package at.mfpjn.workflow.service;

/**
 * Created by Peter on 17-May-15.
 */

import at.mfpjn.workflow.dao.LoggingDao;
import at.mfpjn.workflow.model.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loggingService")
public class LoggingService {

    @Autowired
    private LoggingDao loggingDao;

    public void insertParameter(Logging log) {
        loggingDao.insertLog(log);
    }

    public void updateParameter(Logging log) {
        loggingDao.updateLog(log);
    }

    public Logging getLog(int id) {
        return loggingDao.getLog(id);
    }
}
