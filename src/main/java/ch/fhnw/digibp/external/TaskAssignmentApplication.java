package ch.fhnw.digibp.external;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

@ProcessApplication("Task Assignment App")
public class TaskAssignmentApplication extends ServletProcessApplication {

    public TaskAssignmentListener getTaskListener() {
        return new TaskAssignmentListener();
      }

}
