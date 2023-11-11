package os.spotlight.link.adapter.monday.graph.ql.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.monday.graph.ql.api.constant.Constants;
import os.spotlight.link.adapter.monday.graph.ql.api.converter.MondayResponseConverter;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.adapter.MondayGateway;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MondayGraphQlAdapterImpl implements MondayGateway {
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;
    private final JsonDeserializer deserializer;

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for domain {}.", Constants.API_MONDAY_V2);
        var response = client.sendGetRequest(account.username(), account.token(), Constants.getApiMondayV2(), Constants.getQueryGetAllBoards());
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var getAllBoardsResponse = deserializer.deserialize(content, GetAllBoardsResponse.class);
        return MondayResponseConverter.convertToBoards(getAllBoardsResponse);
    }
}
