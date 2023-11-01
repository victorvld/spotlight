package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.JiraSoftwareCloudAdapterImplConstants;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter.BoardsConverter;
import os.psy.research.spotlight.domain.adpater.schema.SchemaValidator;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.service.PmAdapter;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.RestException;

import java.util.List;

@Slf4j
public class JiraSoftwareCloudAdapterImpl implements PmAdapter {

    private final SchemaValidator validator;

    public JiraSoftwareCloudAdapterImpl(SchemaValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<Board> getAllBoards(Account account) {
        var response = sendGetAllBoardsRequest(account);
        var raw = validator.validate(response, JiraSoftwareCloudAdapterImplConstants.getAllBoardsSchemaClasspath());
        return BoardsConverter.of(raw);
    }

    private String sendGetAllBoardsRequest(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}", account.getUsername(), account.getWebDomain());
        var url = String.format(JiraSoftwareCloudAdapterImplConstants.getAllBoardsUrl(), account.getWebDomain());
        return handleRequest(url, account.getUsername(), account.getToken()).getBody();
    }

    private HttpResponse<String> handleRequest(String url, String username, String token) {
        try {
            return Unirest.get(url).header("Accept", MediaType.APPLICATION_JSON_VALUE).basicAuth(username, token).asString();
        } catch (UnirestException e) {
            throw new RestException("Failed to get response from Jira Software Cloud Rest API.", e);
        }
    }
}
