package os.spotlight.adpater.jira.software.cloud.rest.api;

import lombok.extern.slf4j.Slf4j;
import os.spotlight.adpater.client.HttpClient;
import os.spotlight.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.spotlight.adpater.jira.software.cloud.rest.api.converter.BoardsConverter;
import os.spotlight.adpater.processor.JsonProcessor;
import os.spotlight.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.entity.Account;
import os.spotlight.entity.Board;
import os.spotlight.service.PmAdapter;

import java.util.List;

@Slf4j
public class JiraSoftwareCloudAdapterImpl implements PmAdapter {
    private final JsonProcessor processor;
    private final HttpClient client;
    private final ResponseHandlingStrategy resHandlingStrategy;

    public JiraSoftwareCloudAdapterImpl(JsonProcessor validator, HttpClient client, ResponseHandlingStrategy resHandlingStrategy) {
        this.processor = validator;
        this.client = client;
        this.resHandlingStrategy = resHandlingStrategy;
    }

    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}", account.username(), account.domain());
        var url = Constants.getAllBoardsUrl(account.domain());
        var response = client.sendGetRequest(account.username(), account.token(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var raw = processor.process(content, Constants.getAllBoardsSchemaClasspath());
        return BoardsConverter.of(raw);
    }
}
