package ch.fhnw.digibp.demoressources;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;

@Component
public class AuthorizationGenerator {
    public static final String USER_SANDYCHEEKS = "sandycheeks";
    public static final String USER_SNAILGARRY = "snailgarry";
    public static final String USER_SQUIDWARDTENTACLES = "squidwardtentacles";
    public static final String USER_MRSPUFF = "mrspuff";
    public static final String USER_FLYINGDUTCHMAN = "flyingdutchman";
    public static final String USER_PEARLSKRABS = "pearlskrabs";
    public static final String USER_PLANKTION = "planktion";
    public static final String USER_BOB_SQUAREPANTS = "bobsquarepants";
    public static final String USER_PATRICK_STAR = "patrickstar";
    public static final String USER_MR_KRABS = "mrkrabs";

    public static final String GROUP_HEAD_OF_MANDATE = "headofmandate";
    public static final String GROUP_EMPLOYEE = "employee";
    public static final String GROUP_SERVICEDESK = "servicedesk";
    public static final String GROUP_BILLING = "billing";


    @Autowired
    private IdentityService identityService;

    @Autowired
    private AuthorizationService authorizationService;

    @Value("${camunda-rest.tenantid}")
    private String tenantId;

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

        Tenant tenant = identityService.newTenant(tenantId);
        identityService.saveTenant(tenant);

        User user = identityService.newUser(USER_BOB_SQUAREPANTS);
        user.setFirstName("Bob");
        user.setLastName("Squarepants");
        user.setPassword(USER_BOB_SQUAREPANTS);
        user.setEmail("bobsquarepants@bikinibottom.ch");
        identityService.saveUser(user);

        User user2 = identityService.newUser(USER_PATRICK_STAR);
        user2.setFirstName("Patrick");
        user2.setLastName("Star");
        user2.setPassword(USER_PATRICK_STAR);
        user2.setEmail("patrickstar@bikinibottom.ch");
        identityService.saveUser(user2);

        User user3 = identityService.newUser(USER_MR_KRABS);
        user3.setFirstName("Mr.");
        user3.setLastName("Krabs");
        user3.setPassword(USER_MR_KRABS);
        user3.setEmail("mrkrabs@bikinibottom.ch");
        identityService.saveUser(user3);

        User user4 = identityService.newUser(USER_SNAILGARRY);
        user4.setFirstName("Snail");
        user4.setLastName("Garry");
        user4.setPassword(USER_SNAILGARRY);
        user4.setEmail("snailgarry@bikinibottom.ch");
        identityService.saveUser(user4);

        User user5 = identityService.newUser(USER_SQUIDWARDTENTACLES);
        user5.setFirstName("Squidward");
        user5.setLastName("Tentacles");
        user5.setPassword(USER_SQUIDWARDTENTACLES);
        user5.setEmail("squidwardtentacles@bikinibottom.ch");
        identityService.saveUser(user5);

        User user6 = identityService.newUser(USER_SANDYCHEEKS);
        user6.setFirstName("Sandy");
        user6.setLastName("Cheeks");
        user6.setPassword("sandycheeks");
        user6.setEmail("sandycheeks@bikinibottom.ch");
        identityService.saveUser(user6);

        User user7 = identityService.newUser(USER_MRSPUFF);
        user7.setFirstName("Mrs.");
        user7.setLastName("Puff");
        user7.setPassword(USER_MRSPUFF);
        user7.setEmail("mrspuff@bikinibottom.ch");
        identityService.saveUser(user7);

        User user8 = identityService.newUser(USER_FLYINGDUTCHMAN);
        user8.setFirstName("Flying");
        user8.setLastName("Dutchman");
        user8.setPassword(USER_FLYINGDUTCHMAN);
        user8.setEmail("flyingdutchman@bikinibottom.ch");
        identityService.saveUser(user8);

        User user9 = identityService.newUser(USER_PEARLSKRABS);
        user9.setFirstName("Pearl");
        user9.setLastName("Krabs");
        user9.setPassword(USER_PEARLSKRABS);
        user9.setEmail("pearlskrabs@bikinibottom.ch");
        identityService.saveUser(user9);

        User user10 = identityService.newUser(USER_PLANKTION);
        user10.setFirstName("");
        user10.setLastName("Planktion");
        user10.setPassword(USER_PLANKTION);
        user10.setEmail("planktion@bikinibottom.ch");
        identityService.saveUser(user10);

        Group headofmandateGroup = identityService.newGroup(GROUP_HEAD_OF_MANDATE);
        headofmandateGroup.setName("Head of Mandate");
        headofmandateGroup.setType("WORKFLOW");
        identityService.saveGroup(headofmandateGroup);
        identityService.createTenantGroupMembership(tenant.getId(), headofmandateGroup.getId());

        Group employeeGroup = identityService.newGroup(GROUP_EMPLOYEE);
        employeeGroup.setName("Employee");
        employeeGroup.setType("WORKFLOW");

        identityService.saveGroup(employeeGroup);
        identityService.createTenantGroupMembership(tenant.getId(), employeeGroup.getId());

        Group servicedeskGroup = identityService.newGroup(GROUP_SERVICEDESK);
        servicedeskGroup.setName("Service Desk");
        servicedeskGroup.setType("WORKFLOW");
        identityService.saveGroup(servicedeskGroup);
        identityService.createTenantGroupMembership(tenant.getId(), servicedeskGroup.getId());

        Group billingGroup = identityService.newGroup(GROUP_BILLING);
        billingGroup.setName("Billing");
        billingGroup.setType("WORKFLOW");
        identityService.saveGroup(billingGroup);
        identityService.createTenantGroupMembership(tenant.getId(), billingGroup.getId());

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

        identityService.createMembership(USER_BOB_SQUAREPANTS, GROUP_HEAD_OF_MANDATE);
        identityService.createMembership(USER_PATRICK_STAR, GROUP_HEAD_OF_MANDATE);
        identityService.createMembership(USER_MR_KRABS, GROUP_HEAD_OF_MANDATE);

        identityService.createMembership(USER_SNAILGARRY, GROUP_EMPLOYEE);
        identityService.createMembership(USER_SQUIDWARDTENTACLES, GROUP_EMPLOYEE);
        identityService.createMembership(USER_PLANKTION, GROUP_EMPLOYEE);
        identityService.createMembership(USER_SANDYCHEEKS, GROUP_EMPLOYEE);
        identityService.createMembership(USER_MRSPUFF, GROUP_EMPLOYEE);

        identityService.createMembership(USER_FLYINGDUTCHMAN, GROUP_SERVICEDESK);

        identityService.createMembership(USER_PEARLSKRABS, GROUP_BILLING);

        // authorize groups for tasklist only:
        Authorization headOfMandateTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        headOfMandateTasklistAuth.setGroupId(GROUP_HEAD_OF_MANDATE);
        headOfMandateTasklistAuth.addPermission(ALL);
        //headOfMandateTasklistAuth.setResourceId("*");
        headOfMandateTasklistAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(headOfMandateTasklistAuth);

        /**Authorization employeeTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        employeeTasklistAuth.setGroupId(GROUP_EMPLOYEE);
        employeeTasklistAuth.addPermission(READ);
        //employeeTasklistAuth.setResourceId("tasklist");
        employeeTasklistAuth.setResource(TASK);
        authorizationService.saveAuthorization(employeeTasklistAuth);

        Authorization serviceDeskTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        serviceDeskTasklistAuth.setGroupId(GROUP_SERVICEDESK);
        serviceDeskTasklistAuth.addPermission(READ);
        serviceDeskTasklistAuth.addPermission(UPDATE);
        //serviceDeskTasklistAuth.setResourceId("tasklist");
        serviceDeskTasklistAuth.setResource(TASK);
        authorizationService.saveAuthorization(serviceDeskTasklistAuth);

        Authorization billingTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        billingTasklistAuth.setGroupId(GROUP_BILLING);
        billingTasklistAuth.addPermission(READ);
        //billingTasklistAuth.setResourceId("tasklist");
        billingTasklistAuth.setResource(TASK);
        authorizationService.saveAuthorization(billingTasklistAuth);*/
    }
}