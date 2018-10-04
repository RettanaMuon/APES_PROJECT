package CMSProject.core.user;

import CMSProject.core.Collection;
import CMSProject.core.assets.Comment;
import CMSProject.core.assets.Publication;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static CMSProject.core.parameter.Collection.C_USERS;
import static CMSProject.core.parameter.Common.*;
import static CMSProject.core.parameter.Indexes.*;

public class User extends Collection {
    /* login */
    private String username;
    private String password;
    private String mail;
    /* data */
    private Data data;
    /* comments */
    private ArrayList<Publication> publications = null;
    private ArrayList<Comment>  comments = null;

    public User (String username, String password, String firstname, String lastname,
                 String mail, Date birthday){
        this(
                username,
                password ,
                firstname,
                lastname,
                mail,
                birthday,
                R_USER
        );
    }

    public User (String username, String password, Data data){
        this.username = username;
        this.password = password;
        this.data = data;
    }
    public User (String username, String password, String firstname, String lastname,
                 String mail, Date birthday, String role){
        this.username     = username;
        this.password     = password;
        this.data = new Data(firstname,lastname,role,new Date(), birthday);
        this.mail         = mail;
    }

    @Override
    public Document toDocument(){
        return new Document()
                .append(I_UNAME, username)
                .append(I_PWD, password)
                .append(I_MAIL, mail)
                .append(I_DATA, data.toDocument())
                .append(I_PUBS, publications)
                .append(I_COMS, comments)
                ;
    }

    public void insert() {
        insert(C_USERS);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public Data getData(){
        return data;
    }
    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
