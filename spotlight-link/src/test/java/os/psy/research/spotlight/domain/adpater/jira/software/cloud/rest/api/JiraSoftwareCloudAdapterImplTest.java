package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.processor.JsonProcessor;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.testDataFactory.jira.software.cloud.rest.api.RawDataOm;

import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        when(processor.process(response, Constants.getAllBoardsSchemaClasspath())).thenReturn(rawBoards);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1.0", result.get(0).boardId());
        Assertions.assertEquals("name", result.get(0).boardName());
    }
}
