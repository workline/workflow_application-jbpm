package workflow.rest.endpoint;

import javax.inject.Inject;

import workflow.taskprocessor.IWorkflowTaskHandler;

public class BusinessTaskRestEndpoint {
    @Inject
    private IWorkflowTaskHandler workflowTaskHandler;

    public void finishTask(Long businessTaskId, String taskName) {
        workflowTaskHandler.finishTask(businessTaskId, taskName);
    }
}
