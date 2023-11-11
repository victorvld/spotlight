package os.spotlight.link.adapter.jira.software.cloud.rest.api;

import jira.software.cloud.rest.api.RawBoards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.persistance.entity.Account;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory.RawDataOm;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.link.adapter.tools.processor.JsonProcessor;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

import java.util.Map;

import static org.mockito.Mockito.when;

class JiraSoftwareCloudAdapterImplTest {
    private final JsonProcessor processor = Mockito.mock(JsonProcessor.class);
    private final HttpClient client = Mockito.mock(HttpClient.class);
    private final ResponseHandlingStrategy resHandlingStrategy = Mockito.mock(ResponseHandlingStrategy.class);
    private final JiraSoftwareCloudAdapterImpl underTest = new JiraSoftwareCloudAdapterImpl(processor, client, resHandlingStrategy);

    @Test
    void WhenSendGetAllBoardsRequestThenReturnsEntityBoards() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).domain(url).build();
        var rawBoards = RawDataOm.Boards.complete().build();
        var statusCode = 999;
        when(client.sendGetRequest(username, token, Constants.getAllBoardsUrl(url))).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(processor.process(response, Constants.getAllBoardsSchemaClasspath(), RawBoards.class)).thenReturn(rawBoards);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1.0", result.get(0).boardId());
        Assertions.assertEquals("name", result.get(0).boardName());
    }
}
