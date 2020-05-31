package ch.fhnw.digibp.external.client;

import ch.fhnw.digibp.external.client.mandate.User;
import ch.fhnw.digibp.external.client.mandate.dto.MandateDTO;
import ch.fhnw.digibp.external.client.mandate.dto.MandateRootObjectDTO;
import ch.fhnw.digibp.external.client.mandate.entities.BillableEmployee;
import ch.fhnw.digibp.external.client.mandate.entities.Employee;
import ch.fhnw.digibp.external.client.mandate.entities.Mandate;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RetrieveMandateInformationService{
    private Logger logger = Logger.getLogger(RetrieveMandateInformationService.class.getName());

    @Value("${process.getmandate-information-url}")
    private String getMandateInformationUrl;

    @Value("${camunda-rest.tenantid}")
    private String tenantId;

    @Value("${process.default-mandate}")
    private String defaultMandate;

    @Autowired
    ExternalTaskClient client;

    @Autowired
    ModelMapper modelMapper;


    @PostConstruct
    private void subscribeTopics() {
        client.subscribe("RetrieveMandateInformation")
                .tenantIdIn(tenantId)
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
            try {
                String mandateNameCriterion = externalTask.getBusinessKey();
                if(StringUtils.isBlank(mandateNameCriterion)){
                    mandateNameCriterion = defaultMandate;
                }
                Mandate mandate = retrieveMandateInformation(mandateNameCriterion);

                Map<String, Object> variables = new HashMap<>();
                variables.put("mandate", mandate);
                variables.put("customerContact", mandate.getCustomerContact());
                variables.put("billableEmployees", mandate.getBillableEmployees());
                variables.put("billableEmployeesUsernameCollection",transformBillableEmployeesToUserList(mandate.getBillableEmployees()));
                variables.put("clientManagerUsername",getUserNameFromEmployee(mandate.getClientManager().getEmployee()));
                externalTaskService.complete(externalTask, variables);
            } catch (Exception e) {
                logger.log(Level.SEVERE,e.getMessage());
                externalTaskService.handleBpmnError(externalTask, "RetrieveMandateInformation");
            }                
        }).open();
    }

    private Mandate retrieveMandateInformation(String mandateNameCriterion) throws com.fasterxml.jackson.core.JsonProcessingException {
        String getMandateInformationUrlParameter = getMandateInformationUrl.concat("?mandateName=").concat(mandateNameCriterion);
        HttpResponse<String> response = Unirest.get(getMandateInformationUrlParameter)
        .asString();

        ObjectMapper mapper = new ObjectMapper();
        MandateDTO mandateDTO = mapper.readValue(response.getBody(), MandateRootObjectDTO.class).getMandate();
        return convertMandateDTOToEntity(mandateDTO);
    }

    private List<String> transformBillableEmployeesToUserList(List<BillableEmployee> employees){
        List<String> userList = new ArrayList<>();
        for (BillableEmployee employee : employees) {
            User user = new User(employee.getEmployee().getName(), employee.getEmployee().getEmail());
            userList.add(user.getUsername());
        }
        return userList;
    }

    private String getUserNameFromEmployee(Employee employee){
        User user = new User(employee.getName(), employee.getEmail());
        return user.getUsername();
    }

    private Mandate convertMandateDTOToEntity(MandateDTO mandateDTO) {
        Mandate mandateEntity = modelMapper.map(mandateDTO, Mandate.class);
        return mandateEntity;
    }
}