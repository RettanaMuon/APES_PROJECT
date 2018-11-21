package com.apesdev.S.factory.database;

import com.apesdev.S.core.assets.ErrorCode;
import com.apesdev.S.core.assets._IDs;
import com.apesdev.S.core.database.DB;
import com.apesdev.S.core.nodes.NodeData;
import com.apesdev.S.core.nodes._IDNode;
import com.apesdev.S.core.parameter.Collection;
import com.apesdev.S.core.parameter.Counters;
import com.apesdev.S.core.user.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static com.apesdev.S.core.parameter.Collection.C_CATEGORIES;
import static com.apesdev.S.core.parameter.Collection.C_USERS;
import static com.apesdev.S.core.parameter.Common.R_ADMIN;
import static com.apesdev.S.core.parameter.Indexes.I_UNAME;

public class MockUp {

    public static ErrorCode createAccount(){
        // Create a mock-up admin user
        if(DB.getCollection(C_USERS).find(new Document(I_UNAME,"admin")).first() == null) {
            new User(
                    "admin",
                    "password",
                    "Apemin",
                    "Istrator",
                    "mock-up@admin.com",
                    new Date(),
                    R_ADMIN
            ).insert();
            return new ErrorCode(1, "Mock-up admin created.\n");
        }else{
            return new ErrorCode(0, "An admin user already exist. Mock-up admin had not been created.\n");
        }
    }

    public static ErrorCode createCategory(){
        if(DB.getCollection(C_CATEGORIES).countDocuments() == 0) {
            new _IDNode<_IDs>("Ape Category", null, new ArrayList(), Counters.COUNT_CATEGORIES).insert(Collection.C_CATEGORIES);
            return new ErrorCode(1, "A Mock-Up category has been created.");
        }
        else
            return new ErrorCode(0, "One or many categories already exist. Mock-Up category has not been created.");
    }
}
