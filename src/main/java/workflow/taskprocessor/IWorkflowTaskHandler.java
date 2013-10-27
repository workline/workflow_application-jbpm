package workflow.taskprocessor;

public interface IWorkflowTaskHandler {
    void initTask(Long processRepoItemId, String taskName);

    void finishTask(Long businessTaskId, String taskName);
}
