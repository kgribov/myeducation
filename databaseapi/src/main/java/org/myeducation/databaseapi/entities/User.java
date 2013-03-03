package org.myeducation.databaseapi.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 10.02.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_firstname")
    private String firstName;

    @Column(name = "user_lastname")
    private String lastName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_phonenumber")
    private String phoneNumber;

    @Column(name = "user_createdate")
    private long createDate;

    @Column(name = "user_photo")
    private String photo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "userlogin_id")
    private UserLogin login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }
}
