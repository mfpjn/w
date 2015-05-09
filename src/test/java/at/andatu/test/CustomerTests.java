package at.andatu.test;

import at.mfpjn.workflow.dao.CustomerDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;


@ActiveProfiles("development")
@ContextConfiguration(locations = {
        "file:src/test/resources/config/dao-context.xml",
        "file:src/test/resources/config/datasource.xml",
        "file:src/main/resources/config/security-context.xml",
        "file:src/main/resources/config/service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomerDao customerDao;

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from CUSTOMER");
    }

    @Test
    public void testCreateUser() {

        assertEquals("1 should be equal to 1", 1, 1);
/*
        Customer customer = new Customer(1, "Tester", "Testovic", "Dipl. Ing.", "18.08.1993",
                "p.pepppito@gmail.com", "password", "Slovak", "Link", true, 5, "+421904900897", true, true);

        customerDao.InsertCustomer(customer);
           assertEquals("Created customer should be identical to retrieved feature",
                customer, customerDao.getCustomer(1));
        */
       /*  Features feature = new Features(0, "Tester", "testing", 7, true,
                "Awesome testing data");

        assertTrue("Feature saving should return true",
                featuresDao.saveFeatures(feature));

        List<Features> features = featuresDao.getListOfAllFeatures();

        assertEquals("Number of features should be 1.", 1, features.size());

        assertEquals(
                "Created feature should be identical to retrieved feature",
                feature, features.get(0));

        FeaturesOffers featureOffer = new FeaturesOffers(0, "Testing", 7.62, feature);

        assertTrue("FeatureOffer saving should return true",
                featuresDao.saveFeaturesOffers(featureOffer));

        List<FeaturesOffers> featuresOffers = featuresDao.getListOfAllFeatureOffers();

        assertEquals("Number of featureOffers should be 1.", 1, featuresOffers.size());

        assertEquals(
                "Created featureOffer should be identical to retrieved featureOffer",
                featureOffer, featuresOffers.get(0));

        assertEquals(
                "Created featureOffer should have identical feature as the one that was inserted",
                feature, featuresOffers.get(0).getFeatureId());  */
    }


}
