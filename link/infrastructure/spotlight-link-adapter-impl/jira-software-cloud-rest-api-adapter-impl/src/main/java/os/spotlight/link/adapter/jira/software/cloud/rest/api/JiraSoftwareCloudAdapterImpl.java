package os.spotlight.link.adapter.jira.software.cloud.rest.api;

import jira.software.cloud.rest.api.RawBoards;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.converter.BoardsConverter;
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
        log.info("Retrieving all boards for account: {} and domain {}", account.username(), account.domain());
        var url = Constants.getAllBoardsUrl(account.domain());
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var raw = processor.process(content, Constants.getAllBoardsSchemaClasspath(), RawBoards.class);
        return BoardsConverter.of(raw);
    }

    @Override
    public List<Group> getAllGroups(Account capture, String boardId) {
        return null;
    }
}
