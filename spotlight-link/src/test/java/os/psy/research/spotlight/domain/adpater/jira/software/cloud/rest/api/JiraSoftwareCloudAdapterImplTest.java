package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant.Constants;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.requester.Requester;
import os.psy.research.spotlight.domain.adpater.processor.JsonProcessor;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.RestCallException;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.RestResponseException;
import os.psy.research.spotlight.testDataFactory.RawDataOm;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JiraSoftwareCloudAdapterImplTest {
    private final JsonProcessor validator = Mockito.mock(JsonProcessor.class);
    private final HttpClient client = Mockito.mock(HttpClient.class);
    private final JiraSoftwareCloudAdapterImpl underTest = new JiraSoftwareCloudAdapterImpl(validator, new Requester(client));

    @SneakyThrows
    @Test
    void whenExternalStatusCodeResponseIsSuccessfulThenReturnAllBoards() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).webDomain(url).build();
        var rawBoards = RawDataOm.Boards.complete().build();
        when(client.sendGetRequest(username, token, Constants.getAllBoardsUrl(url))).thenReturn(Map.entry(200, response));
        when(validator.process(response, Constants.getAllBoardsSchemaClasspath())).thenReturn(rawBoards);

        var result = underTest.getAllBoards(acc);

        Assertions.assertEquals("1.0", result.get(0).getBoardId());
        Assertions.assertEquals("name", result.get(0).getBoardName());
    }

    @SneakyThrows
    @Test
    void whenExternalStatusCodeResponseIsNotSuccessfulThenThrowRestResponseException() {
        var username = "username";
        var token = "token";
        var url = "url";
        var response = "response";
        var acc = Account.builder().username(username).token(token).webDomain(url).build();
        var rawBoards = RawDataOm.Boards.complete().build();
        var responseStatusCode = 400;
        when(client.sendGetRequest(username, token, Constants.getAllBoardsUrl(url))).thenReturn(Map.entry(responseStatusCode, response));
        when(validator.process(response, Constants.getAllBoardsSchemaClasspath())).thenReturn(rawBoards);

        var thrown = Assertions.assertThrows(RestResponseException.class, () -> underTest.getAllBoards(acc));
        Assertions.assertEquals(responseStatusCode, thrown.getResponseStatusCode());
        Assertions.assertEquals("Rest Client Error Response.", thrown.getMessage());
    }

    @SneakyThrows
    @Test
    void whenClientThrowsIoExceptionThenThrowRestCallException() {
        var username = "username";
        var token = "token";
        var url = "url";
        var acc = Account.builder().username(username).token(token).webDomain(url).build();
        when(client.sendGetRequest(username, token, Constants.getAllBoardsUrl(url))).thenThrow(IOException.class);

        Assertions.assertThrows(RestCallException.class, () -> underTest.getAllBoards(acc));
    }
}
