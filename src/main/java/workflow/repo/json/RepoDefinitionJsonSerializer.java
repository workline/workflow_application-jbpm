package workflow.repo.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import vrds.model.RepoDefinition;

public class RepoDefinitionJsonSerializer extends JsonSerializer<RepoDefinition> {
    @Override
    public void serialize(RepoDefinition repoDefinition, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {

        jsonGenerator.writeNumber(repoDefinition.getId());
    }
}
