package ch.fhnw.digibp.external;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskAssignmentListener implements TaskListener {

    @Value("${config.email-server}")
    private String emailServer;
    @Value("${config.email-server-port}")
    private int emailServerPort;
    @Value("${config.email-server-user}")
    private String emailUser;
    @Value("${config.email-server-password}")
    private String emailUserPassword;

    private final static Logger LOGGER = Logger.getLogger(TaskAssignmentListener.class.getName());

    @Override
    public void notify(final DelegateTask delegateTask) {

        final String assignee = delegateTask.getAssignee();
        final String taskId = delegateTask.getId();

        if (assignee != null) {

            // Get User Profile from User Management
            final IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            final User user = identityService.createUserQuery().userId(assignee).singleResult();

            if (user != null) {

                // Get Email Address from User Profile
                final String recipient = user.getEmail();

                if (recipient != null && !recipient.isEmpty()) {

                    final Email email = new SimpleEmail();
                    email.setCharset("utf-8");
                    email.setHostName(emailServer);
                    email.setSmtpPort(emailServerPort);
                    email.setAuthentication(emailUser, emailUserPassword);

                    try {
                        email.setFrom("noreply@bikinibottom.ch");
                        email.setSubject("Task assigned: " + delegateTask.getName());
                        email.setMsg(
                                "Please complete: http://localhost:8080/app/tasklist/default/#/?searchQuery=%5B%5D&filter=" + taskId);

                        email.addTo(recipient);

                        email.send();
                        LOGGER.info("Task Assignment Email successfully sent to user '" + assignee + "' with address '"
                                + recipient + "'.");

                    } catch (final Exception e) {
                        LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
                    }

                } else {
                    LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
                }

            } else {
                LOGGER.warning(
                        "Not sending email to user " + assignee + "', user is not enrolled with identity service.");
            }

        }

    }

}