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

    @Column(name="Media")
    private String media;
    
    @Column(name="AccessToken")
    private String accessToken;
    
    @Column(name="AccessTokenSecret")
    private String accessTokenSecret;

    @ManyToOne
    @JoinColumn(name="Customer_Id")
    Customer customerId;

    public UserMediaChannelsParameters() {
    }
    
	public UserMediaChannelsParameters(int id, String media,
			String accessToken, String accessTokenSecret, Customer customerId) {
		super();
		this.id = id;
		this.media = media;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
		this.customerId = customerId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "UserMediaChannelsParameters [id=" + id + ", media=" + media
				+ ", accessToken=" + accessToken + ", accessTokenSecret="
				+ accessTokenSecret + ", customerId=" + customerId + "]";
	}

    
}
