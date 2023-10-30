package os.psy.research.spotlight.domain.adpater.jira;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import os.psy.research.spotlight.domain.adpater.jira.converter.JiraApiResponseConverter;
import os.psy.research.spotlight.domain.adpater.jira.mappers.GetAllBoardsResponse;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

import java.util.List;

@Slf4j
public class JiraApi implements PmAdapter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}.", account.getUsername(), account.getWebDomain());
        var url = String.format(JiraApiConstants.getAllBoardsUrl(), account.getWebDomain());
        var response = Unirest.get(url).header("Accept", MediaType.APPLICATION_JSON_VALUE).basicAuth(account.getUsername(), account.getToken()).asJson();
        var getAllBoardsResponse = objectMapper.readValue(response.getBody().toString(), GetAllBoardsResponse.class);
        return JiraApiResponseConverter.convertToBoard(getAllBoardsResponse);
    }
}
