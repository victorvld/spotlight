package os.psy.research.spotlight.domain.adpater.monday;

import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import os.psy.research.spotlight.domain.adpater.jira.JiraApiConstants;
import os.psy.research.spotlight.domain.adpater.jira.converter.JiraApiResponseConverter;
import os.psy.research.spotlight.domain.adpater.jira.mappers.GetAllBoardsResponse;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

import java.util.List;

@Slf4j
public class MondayApi implements PmAdapter {
    public static final String API_MONDAY_V2 = "https://api.monday.com/v2/";
    @SneakyThrows
    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}.", account.getUsername(), API_MONDAY_V2);
        // TODO: 31/10/2023 Here we might use a graphQL client instead.
        var response = Unirest.get(API_MONDAY_V2)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", account.getToken())
                .header("API-Version", "2023-07")
                .asJson();
        //var getAllBoardsResponse = objectMapper.readValue(response.getBody().toString(), GetAllBoardsResponse.class);
        return null;//JiraApiResponseConverter.convertToBoards(getAllBoardsResponse);
    }
}
