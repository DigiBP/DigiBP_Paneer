
package ch.fhnw.digibp.external.client.mandate.entities;

import java.util.List;

public class Mandate {

    private String name;
    private String isActive;
    private String accountingId;
    private String type;
    private ClientManager clientManager;
    private SalesResponsible salesResponsable;
    private String paymentSchedule;
    private CustomerContact customerContact;
    private List<BillableEmployee> billableEmployees = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getAccountingId() {
        return accountingId;
    }

    public void setAccountingId(String accountingId) {
        this.accountingId = accountingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public SalesResponsible getSalesResponsable() {
        return salesResponsable;
    }

    public void setSalesResponsable(SalesResponsible salesResponsable) {
        this.salesResponsable = salesResponsable;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public List<BillableEmployee> getBillableEmployees() {
        return billableEmployees;
    }

    public void setBillableEmployees(List<BillableEmployee> billableEmployees) {
        this.billableEmployees = billableEmployees;
    }
}
