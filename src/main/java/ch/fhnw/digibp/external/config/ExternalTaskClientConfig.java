/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.external.config;

import ch.fhnw.digibp.demoressources.AuthorizationGenerator;
import org.camunda.bpm.client.ExternalTaskClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
public class ExternalTaskClientConfig {

    @Value("${camunda-rest.url}")
    private String camundaRestUrl;

    @Bean
    @DependsOn("modelMapper")
    public ExternalTaskClient externalTaskClient() {

        return ExternalTaskClient.create()
                .baseUrl(camundaRestUrl)
                .asyncResponseTimeout(29000)
                .disableBackoffStrategy()
                .build();
    }

    @Bean
    public AuthorizationGenerator authorizationGenerator(){
        return new AuthorizationGenerator();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
