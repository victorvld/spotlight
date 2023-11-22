package os.spotlight.link.adapter.monday.graph.ql.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import monday.graph.ql.api.boards.GetAllBoardsResponse;
import monday.graph.ql.api.groups.GetAllGroupsResponse;
import monday.graph.ql.api.items.GetAllItemsResponse;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.monday.graph.ql.api.constant.Constants;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.MondayMapper;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.adapter.MondayGateway;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.persistance.entity.Group;
import os.spotlight.persistance.entity.Item;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MondayGraphQlAdapterImpl implements MondayGateway {
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;
    private final JsonDeserializer deserializer;

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for domain {}.", Constants.getApiMondayV2());
        var response = client.sendGetRequest(account.username(), account.token(), Constants.getApiMondayV2(), Constants.getQueryGetAllBoards());
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var getAllBoardsResponse = deserializer.deserialize(content, GetAllBoardsResponse.class);
        return MondayMapper.of(getAllBoardsResponse);
    }

    @Override
    public List<Group> getAllGroups(Account account, String boardId) {
        log.info("Retrieving all groups for domain {}. And boardId {}", Constants.getApiMondayV2(), boardId);
        var response = client.sendGetRequest(account.username(), account.token(), Constants.getApiMondayV2(), Constants.getQueryGetAllGroups(boardId));
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var getAllGroupsResponse = deserializer.deserialize(content, GetAllGroupsResponse.class);
        return MondayMapper.of(getAllGroupsResponse);
    }

    @Override
    public List<Item> getAllItems(Account account, String boardId, String groupId) {
        log.info("Retrieving all groups for domain {}, boardId {}, and groupId {}", Constants.getApiMondayV2(), boardId, groupId);
        var response = client.sendGetRequest(account.username(), account.token(), Constants.getApiMondayV2(), Constants.getQueryGetAllItems(boardId, groupId));
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var deserialized = deserializer.deserialize(content, GetAllItemsResponse.class);
        return MondayMapper.of(deserialized);
    }
}
