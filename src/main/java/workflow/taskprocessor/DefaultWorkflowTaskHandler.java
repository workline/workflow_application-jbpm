package workflow.taskprocessor;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import vrds.model.EAttributeType;
import vrds.model.RepoItem;
import vrds.model.RepoItemAttribute;
import vrds.model.attributetype.LongAttributeValueHandler;
import workflow.businesstask.IBusinessTaskHandler;

// TODO Transaction
public class DefaultWorkflowTaskHandler implements IWorkflowTaskHandler {
    private static final String WORKFLOW_TASK_ID = "workflowTaskId";
    private static final String BUSINESS_TASKS = "businessTasks";

    @Inject
    private IBusinessTaskHandler businessTaskHandler;

    @Override
    public void initTask(Long processRepoItemId, String taskName) {
        RepoItem processTask = createNewProcessTask(processRepoItemId, taskName);

        List<RepoItem> listOfbusinessTasks = createBusinessTasks();
        // TODO Add business tasks to process task

        for (RepoItem businessTask : listOfbusinessTasks) {
            businessTaskHandler.initTask(businessTask);
        }
    }

    @Override
    public void finishTask(Long businessTaskId, String taskName) {
        // TODO Auto-generated method stub
        // FIXME This is extremely simplified.
        // Different logic must be called depending on the task.
        if (isLast(businessTaskId)) {
            RepoItem businessTask = getBusinessTask(businessTaskId);
            Long workflowTaskId = businessTask.getValue(WORKFLOW_TASK_ID, LongAttributeValueHandler.getInstance());

            finishWorkflowTask(workflowTaskId);
        }
    }

    private List<RepoItem> createBusinessTasks() {
        // TODO Auto-generated method stub
        // businessTask has an attribute 'finished'
        return null;
    }

    private RepoItem getBusinessTask(Long businessTaskId) {
        // TODO Auto-generated method stub
        return null;
    }

    private boolean isLast(Long businessTaskId) {
        // TODO Auto-generated method stub
        return false;
    }

    private RepoItem createNewProcessTask(Long processRepoItemId, String taskName) {
        // TODO Persist processTask
        RepoItem processTask = new RepoItem();

        HashSet<RepoItemAttribute> attributesOfProcessTask = new HashSet<>();

        RepoItemAttribute businessTasksOfProcessTask = new RepoItemAttribute();
        businessTasksOfProcessTask.setNameAndType(BUSINESS_TASKS, EAttributeType.REPO_ITEM);
        attributesOfProcessTask.add(businessTasksOfProcessTask);

        processTask.setRepoItemAttributes(attributesOfProcessTask);

        return processTask;
    }

    private void finishWorkflowTask(Long workflowTaskId) {
        // TODO
    }

}
