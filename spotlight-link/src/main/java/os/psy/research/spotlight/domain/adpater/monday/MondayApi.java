package os.psy.research.spotlight.domain.adpater.monday;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import os.psy.research.spotlight.domain.adpater.monday.converter.MondayResponseConverter;
import os.psy.research.spotlight.domain.adpater.monday.mapper.GetAllBoardsMondayApiResponse;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;

import java.util.List;

@Slf4j
public class MondayApi implements PmAdapter {
    public static final String API_MONDAY_V2 = "https://api.monday.com/v2/";
    public static final String QUERY_GET_ALL_BOARDS_NAME_ID = "{\"query\":\"query{boards(){name id}}\"}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public List<Board> getAllBoards(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}.", account.getUsername(), API_MONDAY_V2);

        // Create an HTTP client
        HttpClient httpClient = HttpClients.createDefault();

        // Define the GraphQL endpoint URL

        // Create an HTTP POST request
        HttpPost httpPost = new HttpPost(API_MONDAY_V2);

        // Set the request headers
        httpPost.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpPost.setHeader("Authorization", account.getToken()); // Replace with your actual API token

        httpPost.setEntity(new StringEntity(QUERY_GET_ALL_BOARDS_NAME_ID));

        // Execute the request and get the response
        HttpResponse response = httpClient.execute(httpPost);

        // Read the response content
        String responseContent = EntityUtils.toString(response.getEntity());

        var getAllBoardsResponse = objectMapper.readValue(responseContent, GetAllBoardsMondayApiResponse.class);

        return MondayResponseConverter.convertToBoards(getAllBoardsResponse);
    }
}
