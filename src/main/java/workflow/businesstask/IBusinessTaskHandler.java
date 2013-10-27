package workflow.businesstask;

import vrds.model.RepoItem;

public interface IBusinessTaskHandler {
    // TODO Split this interface into 2 parts, one that defines methods that are
    // going to be called by the WorkflowTaskProcessor and another that defines methods
    // that are going to be called by the UI

    void initTask(RepoItem businessTask);

    void readVariable(RepoItem businessTask);

    void writeVariable(RepoItem businessTask, String variableName, Object value);

    void finishTask(RepoItem businessTask);

}
