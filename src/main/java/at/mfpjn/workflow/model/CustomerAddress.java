package at.mfpjn.workflow.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "Address", nullable = false, length = 70)
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Column(name = "Address2", nullable = true, length = 70)
    private String address2;

    @Column(name = "ZIP_CODE", nullable = false)
    //@NotEmpty(message="Zip code cannot be empty")
    private int zipCode;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_FK")
    @Valid
    private Customer customerFK;

    @OneToOne
    @JoinColumn(name = "CITY_FK")
    @Valid
    private City cityFK;

    public CustomerAddress() {

    }

    public CustomerAddress(int id, String address, String address2,
                           int zipCode, Customer customerFK, City cityFK) {
        super();
        this.id = id;
        this.address = address;
        this.address2 = address2;
        this.zipCode = zipCode;
        this.customerFK = customerFK;
        this.cityFK = cityFK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomerFK() {
        return customerFK;
    }

    public void setCustomerFK(Customer customerFK) {
        this.customerFK = customerFK;
    }

    public City getCityFK() {
        return cityFK;
    }

    public void setCityFK(City cityFK) {
        this.cityFK = cityFK;
    }

    @Override
    public String toString() {
        return "CustomerAddress [id=" + id + ", address=" + address
                + ", address2=" + address2 + ", zipCode=" + zipCode
                + ", customerFK=" + customerFK + ", cityFK=" + cityFK + "]";
    }
}
