package CMSProject.core.parameter;

/**
 * List of user parameters aiming to make a customizable deployment.
 */
public class Common {
    /* Configurable variables */
    private int minSizeUName = 8;
    private int maxSizeUName = 16;


    /*
        Basics variables
    */
    /* _  | Common */
     public static final String _URL_ROOT = "http://localhost:4200/",
    /* DB | database */
     DB_NAME = "CMSP" ,
     DB_HOST = "mongodb://localhost",
    /* R  | Roles */
    R_ADMIN = "admin",
    R_USER = "user"
             ;


}
