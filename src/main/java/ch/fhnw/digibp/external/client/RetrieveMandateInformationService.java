package ch.fhnw.digibp.external.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ch.fhnw.digibp.external.client.mandate.User;
import ch.fhnw.digibp.external.client.mandate.dto.BillableEmployeeDTO;
import ch.fhnw.digibp.external.client.mandate.dto.EmployeeDTO;
import ch.fhnw.digibp.external.client.mandate.dto.MandateDTO;
import ch.fhnw.digibp.external.client.mandate.dto.MandateRootObjectDTO;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Component
public class RetrieveMandateInformationService{
    private Logger logger = Logger.getLogger(RetrieveMandateInformationService.class.getName());
    @Value("process.getmandate-information-url")
    private String getMandateInformationUrl;

    @Autowired
    ExternalTaskClient client;

    @PostConstruct
    private void subscribeTopics() {
        client.subscribe("RetrieveMandateInformation")
                .tenantIdIn("showcase")
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
            try {
                HttpResponse<String> response = Unirest.get(getMandateInformationUrl)
                .asString();

                ObjectMapper mapper = new ObjectMapper();
                MandateDTO mandateDTO = mapper.readValue(response.getBody(), MandateRootObjectDTO.class).getMandate();

                Map<String, Object> variables = new HashMap<>();
                variables.put("mandate", mandateDTO);
                variables.put("customerContact", mandateDTO.getCustomerContact());
                variables.put("billableEmployees", mandateDTO.getBillableEmployees());
                variables.put("billableEmployeesUsernameCollection",transformBillableEmployeesToUserList(mandateDTO.getBillableEmployees()));
                variables.put("clientManagerUsername",getUserNameFromEmployee(mandateDTO.getClientManager().getEmployee()));
                externalTaskService.complete(externalTask, variables);
            } catch (Exception e) {
                logger.log(Level.SEVERE,e.getMessage());
                externalTaskService.handleBpmnError(externalTask, "RetrieveMandateInformation");
            }                
        }).open();
    }

    private List<String> transformBillableEmployeesToUserList(List<BillableEmployeeDTO> employees){
        List<String> userList = new ArrayList<>();
        for (BillableEmployeeDTO employee : employees) {
            User user = new User(employee.getEmployee().getName(), employee.getEmployee().getEmail());
            userList.add(user.getUsername());
        }
        return userList;
    }

    private String getUserNameFromEmployee(EmployeeDTO employee){
        User user = new User(employee.getName(), employee.getEmail());
        return user.getUsername();
    }
}