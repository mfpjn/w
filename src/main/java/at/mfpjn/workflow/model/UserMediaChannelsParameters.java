package at.mfpjn.workflow.model;

import javax.persistence.*;

/**
 * Created by Peter on 17-May-15.
 */
@Entity
@Table(name="user_media_channels_parameters")
public class UserMediaChannelsParameters {

    @Id
    @GeneratedValue
    @Column(name="Id")
    private int id;

    @Column(name="FacebookToken")
    private String facebookToken;

    @ManyToOne
    @JoinColumn(name="Customer_Id")
    Customer customerId;

    public UserMediaChannelsParameters() {
    }

    public UserMediaChannelsParameters(String facebookToken, Customer customerId) {
        this.facebookToken = facebookToken;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "UserMediaChannelsParametersService{" +
                "id=" + id +
                ", facebookToken='" + facebookToken + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
