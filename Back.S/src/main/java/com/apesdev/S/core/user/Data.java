package com.apesdev.S.core.user;

import com.apesdev.S.core.Jsonable;
import org.bson.Document;

import java.util.Date;

import static com.apesdev.S.core.parameter.Keys.*;

public class Data implements Jsonable {
    private String firstname;
    private String lastname;
    private String role;
    private Date subscriptionDate;
    private Date birthday;

    public Data(String firstname, String lastname, String role, Date subscriptionDate, Date birthday){
        this.firstname    = firstname;
        this.lastname     = lastname;
        this.birthday     = birthday;
        this.role         = role;
        this.subscriptionDate = subscriptionDate;
        this.birthday = birthday;
    }

    @Override
    public Document toDocument(){
        return new Document()
                .append(K_FNAME, firstname)
                .append(K_LNAME, lastname)
                .append(K_BIRTHDAY, birthday)
                .append(K_ROLE, role)
                .append(K_SUBSCRIPTION_DATE, subscriptionDate)
                ;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRole() {
        return role;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public Date getBirthday() {
        return birthday;
    }
}
