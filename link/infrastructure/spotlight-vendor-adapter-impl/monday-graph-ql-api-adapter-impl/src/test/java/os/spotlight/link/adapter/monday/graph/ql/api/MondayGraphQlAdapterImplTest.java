package os.spotlight.link.adapter.monday.graph.ql.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.persistance.entity.Account;
import os.spotlight.link.adapter.monday.graph.ql.api.constant.Constants;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory.RawDataOm;
import os.spotlight.link.factory.adpater.client.HttpClient;
import os.spotlight.link.factory.adpater.processor.JsonDeserializer;
import os.spotlight.link.factory.adpater.response.handling.strategy.ResponseHandlingStrategy;

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
}
