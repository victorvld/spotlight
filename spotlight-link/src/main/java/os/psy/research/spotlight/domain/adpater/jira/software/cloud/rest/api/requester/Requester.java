package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.requester;

import lombok.extern.slf4j.Slf4j;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.RestCallException;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.RestResponseException;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Requester {
    final HttpClient client;

    public Requester(HttpClient client) {
        this.client = client;
    }

    public String sendGetAllBoardsRequest(Account account) {
        log.info("Retrieving all boards for account: {} and domain {}", account.getUsername(), account.getWebDomain());
        var url = Constants.getAllBoardsUrl(account.getWebDomain());
        var response = handleRequest(url, account.getUsername(), account.getToken());
        return handleResponse(response);
    }

    private Map.Entry<Integer, String> handleRequest(String url, String username, String token) {
        try {
            return client.sendGetRequest(username, token, url);
        } catch (IOException e) {
            throw new RestCallException("Failed to get response from Jira Software Cloud Rest API.", e);
        }
    }

    private String handleResponse(Map.Entry<Integer, String> response) {
        if (response.getKey() == 200) {
            return response.getValue();
        }
        throw new RestResponseException(response.getKey(), "Rest Client Error Response.");
    }
}