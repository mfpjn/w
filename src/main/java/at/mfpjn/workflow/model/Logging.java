package at.mfpjn.workflow.model;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by Peter on 17-May-15.
 */
@Entity
@Table(name="logging")
public class Logging {

    @Id
	@GeneratedValue
    @Column(name="Id")
	private int id;

    @Column(name="TextPosted")
    private String textPosted;

    @Column(name="PicturePosted", columnDefinition = "TINYINT(1)")
    private boolean picturePosted;

    @Column(name="PostedAt")
    private DateTime postedAt;

    @Column(name="State")
    private String state;

    @Column(name="MediaName")
    private String mediaName;

    @ManyToOne
    @JoinColumn(name="Customer_Id")
    Customer customerId;

    public Logging() {
    }

    public Logging(String textPosted, boolean picturePosted, DateTime postedAt, String state, String mediaName, Customer customerId) {
        this.textPosted = textPosted;
        this.picturePosted = picturePosted;
        this.postedAt = postedAt;
        this.state = state;
        this.mediaName = mediaName;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextPosted() {
        return textPosted;
    }

    public void setTextPosted(String textPosted) {
        this.textPosted = textPosted;
    }

    public boolean isPicturePosted() {
        return picturePosted;
    }

    public void setPicturePosted(boolean picturePosted) {
        this.picturePosted = picturePosted;
    }

    public DateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(DateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Logging{" +
                "id=" + id +
                ", textPosted='" + textPosted + '\'' +
                ", picturePosted=" + picturePosted +
                ", postedAt=" + postedAt +
                ", state='" + state + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
