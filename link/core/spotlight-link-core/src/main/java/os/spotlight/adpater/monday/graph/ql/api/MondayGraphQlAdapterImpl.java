package os.spotlight.adpater.monday.graph.ql.api;

import lombok.extern.slf4j.Slf4j;
import os.spotlight.adpater.client.HttpClient;
import os.spotlight.adpater.monday.graph.ql.api.constant.Constants;
import os.spotlight.adpater.monday.graph.ql.api.converter.MondayResponseConverter;
import os.spotlight.adpater.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.spotlight.adpater.processor.JsonDeserializer;
import os.spotlight.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.entity.Account;
import os.spotlight.entity.Board;
import os.spotlight.service.PmAdapter;

import java.util.List;

@Slf4j
public class MondayGraphQlAdapterImpl implements PmAdapter {
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;
    private final JsonDeserializer deserializer;

    public MondayGraphQlAdapterImpl(HttpClient client, ResponseHandlingStrategy resHandlingStrategy, JsonDeserializer deserializer) {
        this.client = client;
        this.resHandlingStrategy = resHandlingStrategy;
        this.deserializer = deserializer;
    }

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for domain {}.", Constants.API_MONDAY_V2);
        var response = client.sendGetRequest(account.username(), account.token(), Constants.getApiMondayV2(), Constants.getQueryGetAllBoards());
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var getAllBoardsResponse = deserializer.deserialize(content, GetAllBoardsResponse.class);
        return MondayResponseConverter.convertToBoards(getAllBoardsResponse);
    }
}
