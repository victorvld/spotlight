package os.spotlight.link.adapter.jira.software.cloud.rest.api;

import jira.software.cloud.rest.api.boards.GetAllBoardsResponse;
import jira.software.cloud.rest.api.issues.GetAllItemsResponse;
import jira.software.cloud.rest.api.sprints.GetAllGroupsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.persistance.entity.Account;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory.JiraResponsesOm;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.link.adapter.tools.processor.JsonProcessor;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

import java.util.Map;

import static org.mockito.Mockito.when;

class JiraSoftwareCloudAdapterImplTest {
    private final JsonProcessor processor = Mockito.mock(JsonProcessor.class);
    private final HttpClient client = Mockito.mock(HttpClient.class);
    private final JsonDeserializer deserializer = Mockito.mock(JsonDeserializer.class);
    private final ResponseHandlingStrategy resHandlingStrategy = Mockito.mock(ResponseHandlingStrategy.class);
    private final JiraSoftwareCloudAdapterImpl underTest = new JiraSoftwareCloudAdapterImpl(processor, deserializer, client, resHandlingStrategy);

    @Test
    void WhenSendGetAllBoardsRequestThenReturnsEntityBoards() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).domain(url).build();
        var getAllBoardsResponse = JiraResponsesOm.Boards.complete().build();
        var statusCode = 999;
        when(client.sendGetRequest(username, token, Constants.getAllBoardsUrl(url))).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(processor.process(response, Constants.getAllBoardsSchemaClasspath(), GetAllBoardsResponse.class)).thenReturn(getAllBoardsResponse);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1", result.get(0).boardId());
        Assertions.assertEquals("boardName", result.get(0).boardName());
    }

    @Test
    void WhenSendGetAllGroupsRequestThenReturnsEntityBoards() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).domain(url).build();
        var boardId = 1;
        var getAllGroupsResponse = JiraResponsesOm.Groups.complete().build();
        var statusCode = 999;
        when(client.sendGetRequest(username, token, Constants.getAllGroupsUrl(url, boardId))).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(processor.process(response, Constants.getAllGroupsSchemaClasspath(), GetAllGroupsResponse.class)).thenReturn(getAllGroupsResponse);

        var result = underTest.getAllGroups(acc, String.valueOf(boardId));

        Assertions.assertEquals("1", result.get(0).groupId());
        Assertions.assertEquals("groupName", result.get(0).groupName());
    }

    @Test
    void WhenSendGetAllItemsRequestThenReturnsEntityItems() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).domain(url).build();
        var boardId = "1";
        var groupId = "1";
        var getAllItemsResponse = JiraResponsesOm.Items.complete().build();
        var statusCode = 999;
        when(client.sendGetRequest(username, token, Constants.getAllItemsUrl(url, Integer.valueOf(boardId), Integer.valueOf(groupId)))).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(deserializer.deserialize(response, GetAllItemsResponse.class)).thenReturn(getAllItemsResponse);

        var result = underTest.getAllItems(acc, boardId, groupId);

        Assertions.assertEquals("issueId", result.get(0).id());
        Assertions.assertEquals("issueKey : Issue Summary", result.get(0).name());
        Assertions.assertEquals("Done", result.get(0).status());
        Assertions.assertEquals("1", result.get(0).estimation());
    }
}
