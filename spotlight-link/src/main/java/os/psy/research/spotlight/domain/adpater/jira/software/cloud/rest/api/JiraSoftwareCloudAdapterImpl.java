package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import lombok.extern.slf4j.Slf4j;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter.BoardsConverter;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.requester.Requester;
import os.psy.research.spotlight.domain.adpater.processor.JsonProcessor;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

import java.util.List;

@Slf4j
public class JiraSoftwareCloudAdapterImpl implements PmAdapter {

    private final JsonProcessor processor;
    private final Requester requester;

    public JiraSoftwareCloudAdapterImpl(JsonProcessor validator, Requester requester) {
        this.processor = validator;
        this.requester = requester;
    }

    @Override
    public List<Board> getAllBoards(Account account) {
        var response = requester.sendGetAllBoardsRequest(account);
        var raw = processor.process(response, Constants.getAllBoardsSchemaClasspath());
        return BoardsConverter.of(raw);
    }
}
