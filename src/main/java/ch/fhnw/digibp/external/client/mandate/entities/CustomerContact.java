
package ch.fhnw.digibp.external.client.mandate.entities;

public class CustomerContact {

    private String name;
    private String email;
    private String contactInformaiton;
    private String accessTimeSheet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInformaiton() {
        return contactInformaiton;
    }

    public void setContactInformaiton(String contactInformaiton) {
        this.contactInformaiton = contactInformaiton;
    }

    public String getAccessTimeSheet() {
        return accessTimeSheet;
    }

    public void setAccessTimeSheet(String accessTimeSheet) {
        this.accessTimeSheet = accessTimeSheet;
    }
}
