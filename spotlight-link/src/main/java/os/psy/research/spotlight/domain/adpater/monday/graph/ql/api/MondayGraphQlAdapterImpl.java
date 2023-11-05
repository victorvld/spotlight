package os.psy.research.spotlight.domain.adpater.monday.graph.ql.api;

import lombok.extern.slf4j.Slf4j;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.converter.MondayResponseConverter;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.psy.research.spotlight.domain.adpater.processor.JsonDeserializer;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

import java.util.List;

import static os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.constant.Constants.API_MONDAY_V2;
import static os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.constant.Constants.getApiMondayV2;

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
        log.info("Retrieving all boards for domain {}.", API_MONDAY_V2);
        var response = client.sendGetRequest(account.getUsername(), account.getToken(), getApiMondayV2(), Constants.getQueryGetAllBoards());
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var getAllBoardsResponse = deserializer.deserialize(content, GetAllBoardsResponse.class);
        return MondayResponseConverter.convertToBoards(getAllBoardsResponse);
    }
}
