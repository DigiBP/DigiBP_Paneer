package ch.fhnw.digibp.external.client;

import org.apache.commons.lang3.StringUtils;

public class UserService {

    public String retrieveUserNameFromEmail(String email){
        String emailWithoutProvider = StringUtils.split(email,"@")[0];
        return StringUtils.remove(emailWithoutProvider, '.');
    }

}