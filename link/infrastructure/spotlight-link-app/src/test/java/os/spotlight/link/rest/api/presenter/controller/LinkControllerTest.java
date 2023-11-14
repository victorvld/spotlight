package os.spotlight.link.rest.api.presenter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import os.spotlight.link.rest.api.presenter.dto.GetBoardsRequest;
import os.spotlight.link.rest.api.presenter.dto.GetGroupsRequest;
import os.spotlight.link.rest.api.presenter.mapper.BoardMapper;
import os.spotlight.persistance.entity.LinkService;

import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(os.spotlight.link.rest.api.presenter.controller.LinkController.class)
@ComponentScan(basePackageClasses = {BoardMapper.class})
class LinkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private LinkService mockService;

    @Value("${spotlight.api.link.path}")
    private String baseEndpoint;

    @ParameterizedTest
    @MethodSource("badBoardRequestsProvider")
    void whenGetAllBoardsRequestFieldsDoNotPassValidationRulesThenReturns400(GetBoardsRequest content) throws Exception {
        var json = mapper.writeValueAsString(content);
        var request = get(baseEndpoint + "/boards").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("badGroupRequestsProvider")
    void whenGetAllGroupsRequestFieldsDoNotPassValidationRulesThenReturns400(GetGroupsRequest content) throws Exception {
        var json = mapper.writeValueAsString(content);
        var request = get(baseEndpoint + "/groups").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void whenGetAllBoardRequestPassValidationRulesAndMediaTypesThenReturns200() throws Exception {
        var accountId = "acc1";
        var content = GetBoardsRequest.builder().accountId(accountId).build();
        var captor = ArgumentCaptor.forClass(String.class);
        var json = mapper.writeValueAsString(content);
        var request = get(baseEndpoint + "/boards").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

        mockMvc.perform(request).andExpect(status().isOk());

        verify(mockService, times(1)).getAllBoardsForGivenAccount(captor.capture());
        Assertions.assertEquals(accountId, captor.getValue());
    }

    @Test
    void whenGetAllGroupsRequestPassValidationRulesAndMediaTypesThenReturns200() throws Exception {
        var accountId = "acc1";
        var boardId = "boardId";
        var content = GetGroupsRequest.builder().accountId(accountId).boardId(boardId).build();
        var accCaptor = ArgumentCaptor.forClass(String.class);
        var boardCaptor = ArgumentCaptor.forClass(String.class);
        var json = mapper.writeValueAsString(content);
        var request = get(baseEndpoint + "/groups").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

        mockMvc.perform(request).andExpect(status().isOk());

        verify(mockService, times(1)).getAllGroupsForGivenAccountAndBoardId(accCaptor.capture(), boardCaptor.capture());
        Assertions.assertEquals(accountId, accCaptor.getValue());
        Assertions.assertEquals(boardId, boardCaptor.getValue());
    }

    static Stream<Arguments> badBoardRequestsProvider() {
        return Stream.of(
                Arguments.of(GetBoardsRequest.builder().accountId(" ").build()),
                Arguments.of(GetBoardsRequest.builder().build())
        );
    }

    static Stream<Arguments> badGroupRequestsProvider() {
        return Stream.of(
                Arguments.of(GetGroupsRequest.builder().accountId(" ").build()),
                Arguments.of(GetGroupsRequest.builder().boardId(" ").build()),
                Arguments.of(GetGroupsRequest.builder().build())
        );
    }
}
