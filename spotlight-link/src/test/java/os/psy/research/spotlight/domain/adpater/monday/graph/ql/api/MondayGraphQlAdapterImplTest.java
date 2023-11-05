package os.psy.research.spotlight.domain.adpater.monday.graph.ql.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.mapper.RawBoards;
import os.psy.research.spotlight.domain.adpater.processor.JsonDeserializer;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.testDataFactory.monday.graph.ql.api.RawDataOm;

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
        var rawBoards = RawDataOm.Boards.complete().build();
        var statusCode = 999;
        when(client.sendGetRequest(null, token, Constants.getApiMondayV2(), Constants.getQueryGetAllBoards())).thenReturn(Map.entry(statusCode, response));
        when(resHandlingStrategy.handleResponse(statusCode, response)).thenReturn(response);
        when(deserializer.deserialize(response, RawBoards.class)).thenReturn(rawBoards);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1.0", result.get(0).getBoardId());
        Assertions.assertEquals("name", result.get(0).getBoardName());
    }
}
