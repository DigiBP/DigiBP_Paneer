package ch.fhnw.digibp.external.client.mandate;

import org.apache.commons.lang3.StringUtils;

public class User {

    private String personname;
    private String email;
    private String username;

    public User(String personname, String email){
        this.personname = personname;
        this.email = email;
        this.username = retrieveUserNameFromEmail(email);
    }

    public String getPersonname(){
        return this.personname;
    }

    public String getEmail(){
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    private String retrieveUserNameFromEmail(String email){
        String emailWithoutProvider = StringUtils.split(email,"@")[0];
        return StringUtils.remove(emailWithoutProvider, '.');
    }
}