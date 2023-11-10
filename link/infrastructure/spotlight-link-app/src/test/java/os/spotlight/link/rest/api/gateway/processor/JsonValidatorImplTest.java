package os.spotlight.link.rest.api.gateway.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonValidatorImplTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonValidatorImpl underTest = new JsonValidatorImpl(mapper);

    @Test
    void ValidJsonReturnsNoValidationErrors() {
        var schemaClasspath = "get_all_boards_jira_api_response_schema.json";
        var response = "{\"maxResults\":2,\"startAt\":1,\"total\":5,\"isLast\":false,\"values\":[{\"id\":84,\"self\":\"https://your-domain.atlassian.net/rest/agile/1.0/board/84\",\"name\":\"scrum board\",\"type\":\"scrum\"},{\"id\":92,\"self\":\"https://your-domain.atlassian.net/rest/agile/1.0/board/92\",\"name\":\"kanban board\",\"type\":\"kanban\"}]}";

        var result = underTest.validate(response, schemaClasspath);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void WhenNameOrIdAreMissingThenReturnsValidationErrors() {
        var schemaClasspath = "get_all_boards_jira_api_response_schema.json";
        var response = "{\"maxResults\":2,\"startAt\":1,\"total\":5,\"isLast\":false,\"values\":[{\"self\":\"https://your-domain.atlassian.net/rest/agile/1.0/board/84\",\"type\":\"scrum\"},{\"id\":92,\"self\":\"https://your-domain.atlassian.net/rest/agile/1.0/board/92\",\"name\":\"kanban board\",\"type\":\"kanban\"}]}";

        var result = underTest.validate(response, schemaClasspath);

        Assertions.assertTrue(result.stream().anyMatch(validationMessage -> validationMessage.getMessage().contains("id")));
        Assertions.assertTrue(result.stream().anyMatch(validationMessage -> validationMessage.getMessage().contains("name")));
    }


}