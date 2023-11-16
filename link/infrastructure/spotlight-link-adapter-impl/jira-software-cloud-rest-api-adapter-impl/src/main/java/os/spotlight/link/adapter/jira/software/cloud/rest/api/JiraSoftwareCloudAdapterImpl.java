package os.spotlight.link.adapter.jira.software.cloud.rest.api;

import jira.software.cloud.rest.api.GetAllBoardsResponse;
import jira.software.cloud.rest.api.GetAllGroupsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.converter.JiraMapper;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.adapter.JiraGateway;
import os.spotlight.link.adapter.tools.processor.JsonProcessor;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.persistance.entity.Group;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JiraSoftwareCloudAdapterImpl implements JiraGateway {
    private final JsonProcessor processor;
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain: {}", account.username(), account.domain());
        var url = Constants.getAllBoardsUrl(account.domain());
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var raw = processor.process(content, Constants.getAllBoardsSchemaClasspath(), GetAllBoardsResponse.class);
        return JiraMapper.of(raw);
    }

    @Override
    public List<Group> getAllGroups(Account account, String boardId) {
        log.info("Retrieving all groups for account: {}, domain: {} and board: {}", account.username(), account.domain(), boardId);
        var url = Constants.getAllGroupsUrl(account.domain(), Integer.valueOf(boardId));
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var groups = processor.process(content, Constants.getAllBoardsSchemaClasspath(), GetAllGroupsResponse.class);
        return JiraMapper.of(groups);
    }
}
