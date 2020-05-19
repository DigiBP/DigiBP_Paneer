package ch.fhnw.digibp.external.client.mandate.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "employee"
})
public class SalesResponsibleDTO {

    @JsonProperty("employee")
    private EmployeeDTO employee;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("employee")
    public EmployeeDTO getEmployee() {
        return employee;
    }

    @JsonProperty("employee")
    public void setEmployee(final EmployeeDTO employee) {
        this.employee = employee;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

}