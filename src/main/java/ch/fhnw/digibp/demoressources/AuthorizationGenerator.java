package ch.fhnw.digibp.demoressources;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Permissions.CREATE_INSTANCE;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Permissions.UPDATE;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationGenerator {
  private static final String USER_BOB_SQUAREPANTS = "bobsquarepants";
  private static final String USER_PATRICK_STAR = "patrickstar";
  private static final String USER_MR_KRABS = "mrkrabs";

  @Autowired
  private IdentityService identityService;

  @Autowired
  private AuthorizationService authorizationService;

  private final static Logger LOGGER = Logger.getLogger(AuthorizationGenerator.class.getName());

  @PostConstruct
  public void init() {

      if(identityService.isReadOnly()) {
        LOGGER.info("Identity service provider is Read Only, not creating any demo users.");
        return;
      }

      User singleResult = identityService.createUserQuery().userId(USER_BOB_SQUAREPANTS).singleResult();
      if (singleResult != null) {
        return;
      }

      LOGGER.info("Generating demo data for invoice showcase");

      User user = identityService.newUser(USER_BOB_SQUAREPANTS);
      user.setFirstName("Bob");
      user.setLastName("Squarepants");
      user.setPassword(USER_BOB_SQUAREPANTS);
      user.setEmail("bobsquarepants@bikinibottom.com");
      identityService.saveUser(user);

      User user2 = identityService.newUser(USER_PATRICK_STAR);
      user2.setFirstName("Patrick");
      user2.setLastName("Star");
      user2.setPassword(USER_PATRICK_STAR);
      user2.setEmail("patrickstar@bikinibottom.com");
      identityService.saveUser(user2);

      User user3 = identityService.newUser(USER_MR_KRABS);
      user3.setFirstName("Mr.");
      user3.setLastName("Krabs");
      user3.setPassword(USER_MR_KRABS);
      user3.setEmail("mrkrabs@bikinibottom.com");
      identityService.saveUser(user3);

      User user4 = identityService.newUser("snailgarry");
      user4.setFirstName("Snail");
      user4.setLastName("Garry");
      user4.setPassword("snailgarry");
      user4.setEmail("snailgarry@bikinibottom.com");
      identityService.saveUser(user4);

      User user5 = identityService.newUser("squidwardtentacles");
      user5.setFirstName("Squidward");
      user5.setLastName("Tentacles");
      user5.setPassword("squidwardtentacles");
      user5.setEmail("squidwardtentacles@bikinibottom.com");
      identityService.saveUser(user5);

      User user6 = identityService.newUser("sandycheeks");
      user6.setFirstName("Sandy");
      user6.setLastName("Cheeks");
      user6.setPassword("sandycheeks");
      user6.setEmail("sandycheeks@bikinibottom.com");
      identityService.saveUser(user6);

      User user7 = identityService.newUser("mrspuff");
      user7.setFirstName("Mrs.");
      user7.setLastName("Puff");
      user7.setPassword("mrspuff");
      user7.setEmail("mrspuff@bikinibottom.com");
      identityService.saveUser(user7);

      User user8 = identityService.newUser("flyingdutchman");
      user8.setFirstName("Flying");
      user8.setLastName("Dutchman");
      user8.setPassword("flyingdutchman");
      user8.setEmail("flyingdutchman@bikinibottom.com");
      identityService.saveUser(user8);

      User user9 = identityService.newUser("pearlskrabs");
      user9.setFirstName("Pearl");
      user9.setLastName("Krabs");
      user9.setPassword("pearlskrabs");
      user9.setEmail("pearlskrabs@bikinibottom.com");
      identityService.saveUser(user9);

      User user10 = identityService.newUser("planktion");
      user10.setFirstName("");
      user10.setLastName("Planktion");
      user10.setPassword("planktion");
      user10.setEmail("planktion@bikinibottom.com");
      identityService.saveUser(user10);

      Group headofmandateGroup = identityService.newGroup("headofmandate");
      headofmandateGroup.setName("Head of Mandate");
      headofmandateGroup.setType("WORKFLOW");
      identityService.saveGroup(headofmandateGroup);

      Group employeeGroup = identityService.newGroup("employee");
      employeeGroup.setName("Employee");
      employeeGroup.setType("WORKFLOW");
      identityService.saveGroup(employeeGroup);

      Group servicedeskGroup = identityService.newGroup("servicedesk");
      servicedeskGroup.setName("Service Desk");
      servicedeskGroup.setType("WORKFLOW");
      identityService.saveGroup(servicedeskGroup);

      Group billingGroup = identityService.newGroup("billing");
      billingGroup.setName("Service Desk");
      billingGroup.setType("WORKFLOW");
      identityService.saveGroup(billingGroup);

      // create group
      if(identityService.createGroupQuery().groupId(Groups.CAMUNDA_ADMIN).count() == 0) {
        Group camundaAdminGroup = identityService.newGroup(Groups.CAMUNDA_ADMIN);
        camundaAdminGroup.setName("camunda BPM Administrators");
        camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
        identityService.saveGroup(camundaAdminGroup);
      }

      // create ADMIN authorizations on all built-in resources
      for (Resource resource : Resources.values()) {
        if(authorizationService.createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
          AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
          userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
          userAdminAuth.setResource(resource);
          userAdminAuth.setResourceId(ANY);
          userAdminAuth.addPermission(ALL);
          authorizationService.saveAuthorization(userAdminAuth);
        }
      }

      identityService.createMembership(USER_BOB_SQUAREPANTS, "headofmandate");
      identityService.createMembership(USER_PATRICK_STAR, "headofmandate");
      identityService.createMembership(USER_MR_KRABS, "headofmandate");

      identityService.createMembership("snailgarry", "employee");
      identityService.createMembership("squidwardtentacles", "employee");
      identityService.createMembership("planktion", "employee");
      identityService.createMembership("sandycheeks", "employee");
      identityService.createMembership("mrspuff", "employee");

      identityService.createMembership("flyingdutchman", "servicedesk");

      identityService.createMembership("pearlskrabs", "billing");

      // authorize groups for tasklist only:
      Authorization headOfMandateTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      headOfMandateTasklistAuth.setGroupId("headofmandate");
      headOfMandateTasklistAuth.addPermission(READ);
      headOfMandateTasklistAuth.addPermission(UPDATE);
      headOfMandateTasklistAuth.addPermission(CREATE_INSTANCE);
      headOfMandateTasklistAuth.setResourceId("tasklist");
      headOfMandateTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(headOfMandateTasklistAuth);

      Authorization employeeTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      employeeTasklistAuth.setGroupId("employee");
      employeeTasklistAuth.addPermission(READ);
      employeeTasklistAuth.setResourceId("tasklist");
      employeeTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(employeeTasklistAuth);

      Authorization serviceDeskTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      serviceDeskTasklistAuth.setGroupId("servicedesk");
      serviceDeskTasklistAuth.addPermission(READ);
      serviceDeskTasklistAuth.addPermission(UPDATE);
      serviceDeskTasklistAuth.setResourceId("tasklist");
      serviceDeskTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(serviceDeskTasklistAuth);

      Authorization billingTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      billingTasklistAuth.setGroupId("billing");
      billingTasklistAuth.addPermission(READ);
      billingTasklistAuth.setResourceId("tasklist");
      billingTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(billingTasklistAuth);
    }
}