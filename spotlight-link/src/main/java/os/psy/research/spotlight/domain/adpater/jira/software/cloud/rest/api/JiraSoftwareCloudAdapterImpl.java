package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import lombok.extern.slf4j.Slf4j;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter.BoardsConverter;
import os.psy.research.spotlight.domain.adpater.processor.JsonProcessor;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

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
        log.info("Retrieving all boards for account: {} and domain {}", account.getUsername(), account.getWebDomain());
        var url = Constants.getAllBoardsUrl(account.getWebDomain());
        var response = client.sendGetRequest(account.getUsername(), account.getToken(), url);
        var content = resHandlingStrategy.handleResponse(response.getKey(), response.getValue());
        var raw = processor.process(content, Constants.getAllBoardsSchemaClasspath());
        return BoardsConverter.of(raw);
    }
}
