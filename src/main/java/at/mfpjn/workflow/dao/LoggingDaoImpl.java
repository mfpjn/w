package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.Logging;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Peter on 17-May-15.
 */
@Repository
@Transactional
@Component("loggingDao")
public class LoggingDaoImpl implements LoggingDao {

    @Autowired
    private SessionFactory sessionFactoryMain;

    public Session session() {
        return sessionFactoryMain.getCurrentSession();
    }

    @Transactional
    public void insertLog(Logging log) {
        session().save(log);
    }

    @Transactional
    public void updateLog(Logging log) {
        session().update(log);
    }

    @SuppressWarnings("unchecked")
    public Logging getLog(int id){
        return (Logging)session().createQuery("from Logging where Id = :id").setParameter("id", id).uniqueResult();
    }
}
