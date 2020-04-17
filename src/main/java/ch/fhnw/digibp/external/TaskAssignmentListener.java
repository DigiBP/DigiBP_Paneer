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

public class TaskAssignmentListener implements TaskListener {

    private static final String HOST = "mail.bikinibottom.ch";
    private static final String USER = "admin@bikinibottom.ch";
    private static final String PWD = "toomanysecrets";

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
                    email.setHostName(HOST);
                    email.setAuthentication(USER, PWD);

                    try {
                        email.setFrom("noreply@cbikinibottom.ch");
                        email.setSubject("Task assigned: " + delegateTask.getName());
                        email.setMsg(
                                "Please complete: http://localhost:8080/camunda/app/tasklist/default/#/task=" + taskId);

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