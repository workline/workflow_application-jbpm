package workflow.businesstask;

import java.util.List;

import vrds.model.EAttributeType;
import vrds.model.MetaAttribute;
import vrds.model.RepoItem;
import vrds.model.RepoItemAttribute;
import vrds.model.attributetype.StringAttributeValueHandler;
import workflow.model.ProcessVariableDefinition;

public class DefaultBusinessTaskHandler implements IBusinessTaskHandler {
    /*
     * BusinessTask process (process instance) task (workflow task)
     */

    private static final String DYNAMIC_TAG = "dynamicTag";
    private static final String PROCESS = "process";
    private static final String TASK_SPECIFIC_PROCESS_VARIABLES_DEFINITION = "taskSpecificProcessVariablesDefinition";
    private static final String IO_VARIABLE_SOURCE = "ioVariableSource";
    private static final String INPUT_BEHAVIOUR_SOURCE = "inputBehaviourSource";

    @Override
    public void initTask(RepoItem businessTask) {
        preProcess(businessTask);
        initialIO(businessTask);
        // TODO notify actors
    }

    @Override
    public void readVariable(RepoItem businessTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeVariable(RepoItem businessTask, String variableName, Object value) {
        RepoItem task = getBusinessTaskDefinition(businessTask);

        RepoItemAttribute attribute = task.getAttribute(variableName);
        if (attribute == null) {
            // TODO Where and what kind of exception should be thrown.
            throw new RuntimeException();
        } else {
            attribute.setValue(value);
        }

        String inputBehaviourSource = task.getValue(INPUT_BEHAVIOUR_SOURCE, StringAttributeValueHandler.getInstance());
        callInputBehaviourService(businessTask.getId(), inputBehaviourSource);

        setContextDependentIO(task);
    }

    public void set(String taskVariableName, Object value) {

    }

    @Override
    public void finishTask(RepoItem businessTask) {
        postProcess(businessTask);
        close(businessTask);
    }

    private void preProcess(RepoItem businessTask) {

    }

    private void initialIO(RepoItem businessTask) {
        RepoItem businessTaskDefinition = getBusinessTaskDefinition(businessTask);

        String taskSpecificProcessVariableDefinitionData = businessTaskDefinition.getValue(TASK_SPECIFIC_PROCESS_VARIABLES_DEFINITION,
                StringAttributeValueHandler.getInstance());
        List<ProcessVariableDefinition> taskSpecificProcessVariableDefinitionList = parseIoVariableSourceData(taskSpecificProcessVariableDefinitionData);

        for (ProcessVariableDefinition taskSpecificProcessVariableDefinition : taskSpecificProcessVariableDefinitionList) {
            RepoItemAttribute ioAttribute = new RepoItemAttribute();
            ioAttribute.setRepoItem(businessTask);
            ioAttribute.setNameAndType(taskSpecificProcessVariableDefinition.getName(), taskSpecificProcessVariableDefinition.getType().getInputVariableType()
                    .getAttributeType());
            persist(ioAttribute);
        }

        setContextDependentIO(businessTask);
    }

    private void postProcess(RepoItem businessTask) {
        // TODO Auto-generated method stub

    }

    private void close(RepoItem businessTask) {
        // TODO Auto-generated method stub

    }

    private void callInputBehaviourService(Long taskId, String inputBehaviourSource) {
        // TODO Auto-generated method stub

    }

    private void setContextDependentIO(RepoItem businessTask) {
        String ioVariableSourceData = businessTask.getValue(IO_VARIABLE_SOURCE, StringAttributeValueHandler.getInstance());
        List<ProcessVariableDefinition> contextDependentProcessVariableDefinitionList = parseIoVariableSourceData(ioVariableSourceData);

        // TODO Remove previous, not used attributes
        for (ProcessVariableDefinition contextDependentProcessVariableDefinition : contextDependentProcessVariableDefinitionList) {
            RepoItemAttribute ioAttribute = new RepoItemAttribute();
            ioAttribute.setRepoItem(businessTask);
            ioAttribute.setNameAndType(contextDependentProcessVariableDefinition.getName(), contextDependentProcessVariableDefinition.getType()
                    .getInputVariableType().getAttributeType());

            MetaAttribute dynamicTag = new MetaAttribute();
            dynamicTag.setOwnerAttribute(ioAttribute);
            dynamicTag.setNameAndType(DYNAMIC_TAG, EAttributeType.STRING);

            persist(ioAttribute);
        }
    }

    private List<ProcessVariableDefinition> parseIoVariableSourceData(String staticIO) {
        /*
         * accessRight;REPO::AccessRight;PROCESS;ALL::REPO::AccessRight
         */

        // TODO Auto-generated method stub
        return null;
    }

    private RepoItem getBusinessTaskDefinition(RepoItem businessTask) {
        // TODO Auto-generated method stub
        return null;
    }

    private void persist(RepoItemAttribute ioAttribute) {
        // TODO Auto-generated method stub

    }

}
