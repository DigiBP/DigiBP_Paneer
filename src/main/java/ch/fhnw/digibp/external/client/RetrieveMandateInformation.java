package ch.fhnw.digibp.external.client;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.digibp.external.ProcessApplication;
import ch.fhnw.digibp.external.client.mandate.Mandate;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Component
public class RetrieveMandateInformation {

    @Autowired
    ExternalTaskClient client;

    @PostConstruct
    private void subscribeTopics() {

        client.subscribe("RetrieveMandateInformation")
                .tenantIdIn(ProcessApplication.TENANT_ID)
                .handler((final ExternalTask externalTask, final ExternalTaskService externalTaskService) -> {
                    try {
                        HttpResponse<String> response = Unirest.get("https://putsreq.com/0t35TtKnuiTvpOox3iNo")
                        .asString();

                        ObjectMapper mapper = new ObjectMapper();
                        Mandate mandate = mapper.readValue(response.getBody(), Mandate.class);

                        Map<String, Object> variables = new HashMap<>();
                        variables.put("mandate", mandate.getMandate());
                        variables.put("customerContact", mandate.getMandate().getCustomerContact());
                        variables.put("billableEmployees", mandate.getMandate().getBillableEmployees());
                        variables.put("nbBillableEmployees", mandate.getMandate().getBillableEmployees().size());
                        externalTaskService.complete(externalTask, variables);
                    } catch (final Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();
    }
}