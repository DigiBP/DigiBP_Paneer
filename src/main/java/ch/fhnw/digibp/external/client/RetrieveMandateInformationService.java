package ch.fhnw.digibp.external.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.digibp.external.client.mandate.BillableEmployee;
import ch.fhnw.digibp.external.client.mandate.Mandate;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Component
public class RetrieveMandateInformationService{
    private Logger logger = Logger.getLogger(RetrieveMandateInformationService.class.getName());

    @Autowired
    ExternalTaskClient client;


    @PostConstruct
    private void subscribeTopics() {
        client.subscribe("RetrieveMandateInformation")
                .tenantIdIn("showcase")
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
            try {
                HttpResponse<String> response = Unirest.get("https://putsreq.com/0t35TtKnuiTvpOox3iNo")
                .asString();

                ObjectMapper mapper = new ObjectMapper();
                Mandate mandate = mapper.readValue(response.getBody(), Mandate.class);

                Map<String, Object> variables = new HashMap<>();
                variables.put("mandate", mandate.getMandate());
                variables.put("customerContact", mandate.getMandate().getCustomerContact());
                variables.put("billableEmployees", mandate.getMandate().getBillableEmployees());
                variables.put("billableEmployeesCollection",transformBillableEmployeesToUserList(mandate.getMandate().getBillableEmployees()));
                externalTaskService.complete(externalTask, variables);
            } catch (Exception e) {
                logger.log(Level.SEVERE,e.getMessage());
                externalTaskService.handleBpmnError(externalTask, "RetrieveMandateInformation");
            }                
        }).open();
    }

    private List<String> transformBillableEmployeesToUserList(List<BillableEmployee> employees){
        List<String> userList = new ArrayList<>();
        for (BillableEmployee employee : employees) {
            userList.add(retrieveUserNameFromEmail(employee.getEmployee().getEmail()));
        }
        return userList;
    }

    private String retrieveUserNameFromEmail(String email){
        String emailWithoutProvider = StringUtils.split(email,"@")[0];
        return StringUtils.remove(emailWithoutProvider, '.');
    }
}