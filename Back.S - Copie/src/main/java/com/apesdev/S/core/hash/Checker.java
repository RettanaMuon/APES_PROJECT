package com.apesdev.S.core.hash;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.database.DB;
import org.bson.Document;

import static com.apesdev.S.core.hash.Parameter.*;
import static com.apesdev.S.core.parameter.Collection.C_USERS;
import static com.apesdev.S.core.parameter.Indexes.I_PWD;
import static com.apesdev.S.core.parameter.Indexes.I_UNAME;

public class Checker extends ErrorCode {

    private String arg1, arg2, type;

    public Checker ( String arg1, String arg2, String type){
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = type;
        check();
    }

    private void check(){
        switch (type){
            case T_LOGIN :
                checkLogin(arg1,arg2);
        }
    }

    /**
     * state 1 : login success
     * state -1 : unknown user
     * state -2 : bad password
     * state -3 : username or password void
     * @param unameOrMail
     * @param password
     */
    private void checkLogin(String unameOrMail, String password){
        if (unameOrMail.equals("")){
            state = -3;
            message += "Username is null\n";
        }
        if (password.equals("")){
            state = -3;
            message += "Password is null\n";
        }
        if (state != -3) {
            Document user = (Document) DB.
                    getCollection(C_USERS).
                    find(new Document(I_UNAME, unameOrMail))
                    .first();
            if (user == null) {
                state = -1;
                message = "Login failed : unknown user.\n";
            } else {
                String hashedPassword = user.getString(I_PWD);
                // TODO : hash function
                if (password.equals(hashedPassword)) {
                    state = 1;
                    message = "Login sucess for " + user.getString(I_UNAME) + ".\n";
                } else {
                    state = -2;
                    message = "Error : Login failed for " + user.getString(I_UNAME) + ". bad password.\n";
                }
            }
        }
    }

}
