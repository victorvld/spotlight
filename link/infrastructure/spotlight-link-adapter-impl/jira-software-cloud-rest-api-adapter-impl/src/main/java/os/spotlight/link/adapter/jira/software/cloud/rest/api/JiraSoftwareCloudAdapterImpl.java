package os.spotlight.link.adapter.jira.software.cloud.rest.api;

import jira.software.cloud.rest.api.boards.GetAllBoardsResponse;
import jira.software.cloud.rest.api.issues.GetAllItemsResponse;
import jira.software.cloud.rest.api.sprints.GetAllGroupsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.mapper.JiraMapper;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.adapter.JiraGateway;
import os.spotlight.link.adapter.tools.processor.JsonProcessor;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.persistance.entity.Group;
import os.spotlight.persistance.entity.Item;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
public class JiraSoftwareCloudAdapterImpl implements JiraGateway {
    private final JsonProcessor processor;
    private final JsonDeserializer deserializer;
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain: {}", account.username(), account.domain());
        var url = Constants.getAllBoardsUrl(account.domain());
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var processedRes = processor.process(content, Constants.getAllBoardsSchemaClasspath(), GetAllBoardsResponse.class);
        return JiraMapper.of(processedRes);
    }

    @Override
    public List<Group> getAllGroups(Account account, String boardId) {
        log.info("Retrieving all groups for account: {}, domain: {} and board: {}", account.username(), account.domain(), boardId);
        var url = Constants.getAllGroupsUrl(account.domain(), Integer.valueOf(boardId));
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var processedRes = processor.process(content, Constants.getAllGroupsSchemaClasspath(), GetAllGroupsResponse.class);
        return JiraMapper.of(processedRes);
    }

    @Override
    public List<Item> getAllItems(Account account, String boardId, String groupId) {
        log.info("Retrieving all items for account: {}, domain: {}  board: {}, and group: {}", account.username(), account.domain(), boardId, groupId);
        var url = Constants.getAllItemsUrl(account.domain(), Integer.valueOf(boardId), Integer.valueOf(groupId));
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var processedRes = deserializer.deserialize(content, GetAllItemsResponse.class);
        return JiraMapper.of(processedRes);
    }
}
