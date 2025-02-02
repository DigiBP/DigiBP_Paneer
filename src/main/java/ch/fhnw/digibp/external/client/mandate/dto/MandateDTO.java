
package ch.fhnw.digibp.external.client.mandate.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "isActive",
    "accountingId",
    "type",
    "clientManager",
    "salesResponsable",
    "paymentSchedule",
    "customerContact",
    "billableEmployees"
})
public class MandateDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("isActive")
    private String isActive;
    @JsonProperty("accountingId")
    private String accountingId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("clientManager")
    private ClientManagerDTO clientManager;
    @JsonProperty("salesResponsable")
    private SalesResponsibleDTO salesResponsable;
    @JsonProperty("paymentSchedule")
    private String paymentSchedule;
    @JsonProperty("customerContact")
    private CustomerContactDTO customerContact;
    @JsonProperty("billableEmployees")
    private List<BillableEmployeeDTO> billableEmployees = null;
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

    @JsonProperty("isActive")
    public String getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("accountingId")
    public String getAccountingId() {
        return accountingId;
    }

    @JsonProperty("accountingId")
    public void setAccountingId(String accountingId) {
        this.accountingId = accountingId;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("clientManager")
    public ClientManagerDTO getClientManager() {
        return clientManager;
    }

    @JsonProperty("clientManager")
    public void setClientManager(ClientManagerDTO clientManager) {
        this.clientManager = clientManager;
    }

    @JsonProperty("salesResponsable")
    public SalesResponsibleDTO getSalesResponsable() {
        return salesResponsable;
    }

    @JsonProperty("salesResponsable")
    public void setSalesResponsable(SalesResponsibleDTO salesResponsable) {
        this.salesResponsable = salesResponsable;
    }

    @JsonProperty("paymentSchedule")
    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    @JsonProperty("paymentSchedule")
    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    @JsonProperty("customerContact")
    public CustomerContactDTO getCustomerContact() {
        return customerContact;
    }

    @JsonProperty("customerContact")
    public void setCustomerContact(CustomerContactDTO customerContact) {
        this.customerContact = customerContact;
    }

    @JsonProperty("billableEmployees")
    public List<BillableEmployeeDTO> getBillableEmployees() {
        return billableEmployees;
    }

    @JsonProperty("billableEmployees")
    public void setBillableEmployees(List<BillableEmployeeDTO> billableEmployees) {
        this.billableEmployees = billableEmployees;
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
