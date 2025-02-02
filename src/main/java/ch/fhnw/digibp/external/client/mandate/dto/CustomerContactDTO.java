
package ch.fhnw.digibp.external.client.mandate.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "email",
    "contactInformaiton",
    "accessTimeSheet"
})
public class CustomerContactDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("contactInformaiton")
    private String contactInformaiton;
    @JsonProperty("accessTimeSheet")
    private String accessTimeSheet;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("contactInformaiton")
    public String getContactInformaiton() {
        return contactInformaiton;
    }

    @JsonProperty("contactInformaiton")
    public void setContactInformaiton(String contactInformaiton) {
        this.contactInformaiton = contactInformaiton;
    }

    @JsonProperty("accessTimeSheet")
    public String getAccessTimeSheet() {
        return accessTimeSheet;
    }

    @JsonProperty("accessTimeSheet")
    public void setAccessTimeSheet(String accessTimeSheet) {
        this.accessTimeSheet = accessTimeSheet;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
