package workflow.workitem;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

public class DefaultWorkItemHandler implements WorkItemHandler {

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        long processInstanceId = workItem.getProcessInstanceId();

        String processName = getProcessName();
        String taskName = (String) workItem.getParameter("taskName");

        callTaskProcessor(processInstanceId, processName, taskName);

    }

    private void callTaskProcessor(long processInstanceId, String processName, String taskName) {
        // TODO Auto-generated method stub

    }

    private String getProcessName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // TODO Auto-generated method stub

    }

}
