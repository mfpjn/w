package at.mfpjn.workflow.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.mfpjn.workflow.model.UserMediaChannelsParameters;

/**
 * Created by Peter on 17-May-15.
 */
@Repository
@Transactional
@Component("userMediaChannelsParametersDao")
public class UserMediaChannelsParametersDaoImpl implements UserMediaChannelsParametersDao {

    @Autowired
    private SessionFactory sessionFactoryMain;

    public Session session() {
        return sessionFactoryMain.getCurrentSession();
    }

    @Transactional
    public void insertParameter(UserMediaChannelsParameters parameter) {
        session().save(parameter);
    }

    @Transactional
    public void updateParameter(UserMediaChannelsParameters parameter) {
        session().update(parameter);
    }

    @SuppressWarnings("unchecked")
    public UserMediaChannelsParameters getParameter(int id){
        return (UserMediaChannelsParameters)session().createQuery("from UserMediaChannelsParameters where Id = :id").setParameter("id", id).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public UserMediaChannelsParameters getTwitterParameter(int id){
        return (UserMediaChannelsParameters)session().createQuery("from UserMediaChannelsParameters where Customer_Id = :id and media = 'Twitter'").setParameter("id", id).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public UserMediaChannelsParameters getFacebookParameter(int id){
        return (UserMediaChannelsParameters)session().createQuery("from UserMediaChannelsParameters where Customer_Id = :id and media = 'Facebook'").setParameter("id", id).uniqueResult();
    }
    
    public void deleteTwitterParameters(int id){
        session().createQuery("delete UserMediaChannelsParameters where Customer_Id = :id and media = 'Twitter'").setParameter("id", id).executeUpdate();
    }
}
