package os.spotlight.link.adapter.monday.graph.ql.api;

import monday.graph.ql.api.GetAllGroupsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.persistance.entity.Account;
import os.spotlight.link.adapter.monday.graph.ql.api.constant.Constants;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory.RawDataOm;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

import java.util.Map;

import static org.mockito.Mockito.when;

class MondayGraphQlAdapterImplTest {
    private final JsonDeserializer deserializer = Mockito.mock(JsonDeserializer.class);
    private final HttpClient client = Mockito.mock(HttpClient.class);
    private final ResponseHandlingStrategy resHandlingStrategy = Mockito.mock(ResponseHandlingStrategy.class);
    private final MondayGraphQlAdapterImpl underTest = new MondayGraphQlAdapterImpl(client, resHandlingStrategy, deserializer);

    @Test
    void WhenSendGetAllBoardsRequestThenReturnsEntityBoards() {
        var token = "token";
        var response = "response";
        var acc = Account.builder().token(token).build();
        var rawBoards = RawDataOm.Boards.complete();
        var statusCode = 999;
        when(client.sendGetRequest(null, token, Constants.getApiMondayV2(), Constants.getQueryGetAllBoards())).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(deserializer.deserialize(response, GetAllBoardsResponse.class)).thenReturn(rawBoards);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1.0", result.get(0).boardId());
        Assertions.assertEquals("name", result.get(0).boardName());
    }

    @Test
    void WhenSendGetAllGroupsRequestThenReturnsEntityBoards() {
        var token = "token";
        var response = "response";
        var boardId = "boardId";
        var acc = Account.builder().token(token).build();
        var getAllGroupsResponse = RawDataOm.Groups.complete();
        var statusCode = 999;
        when(client.sendGetRequest(null, token, Constants.getApiMondayV2(), Constants.getQueryGetAllGroups(boardId))).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(deserializer.deserialize(response, GetAllGroupsResponse.class)).thenReturn(getAllGroupsResponse);

        var result = underTest.getAllGroups(acc, boardId);

        Assertions.assertEquals("groupId", result.get(0).groupId());
        Assertions.assertEquals("title", result.get(0).groupName());
    }
}
