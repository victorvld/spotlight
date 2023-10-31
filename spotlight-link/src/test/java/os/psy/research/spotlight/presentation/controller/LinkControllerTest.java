package os.psy.research.spotlight.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import os.psy.research.spotlight.domain.service.LinkService;
import os.psy.research.spotlight.presentation.Mapper.BoardMapper;
import os.psy.research.spotlight.presentation.dto.GetBoardsRequest;
import os.psy.research.spotlight.testDataFactory.EntityObjectMother;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static os.psy.research.spotlight.presentation.controller.LinkController.SPOTLIGHT_V_1_LINK_API;

@WebMvcTest(LinkController.class)
@ComponentScan(basePackageClasses = {BoardMapper.class})
class LinkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private LinkService mockService;
    public static final String ENDPOINT = SPOTLIGHT_V_1_LINK_API + "/boards";

    @Test
    void whenGetBoardRequestPassValidationRulesAndMediaTypesThenReturns200() throws Exception {
        var accountId = "acc1";
        var content = GetBoardsRequest.builder().accountId(accountId).build();
        var captor = ArgumentCaptor.forClass(String.class);
        var json = mapper.writeValueAsString(content);
        var request = get(ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);
        when(mockService.getAllBoardsForGivenAccount(accountId)).thenReturn(Collections.singletonList(EntityObjectMother.complete().build()));

        mockMvc.perform(request).andExpect(status().isOk());

        verify(mockService, times(1)).getAllBoardsForGivenAccount(captor.capture());
        Assertions.assertEquals(accountId, captor.getValue());
    }

    @ParameterizedTest
    @MethodSource("badRequestsProvider")
    void whenIncomingRequestFieldsDoNotPassValidationRulesThenReturns400(GetBoardsRequest content) throws Exception {
        var json = mapper.writeValueAsString(content);
        var request = get(ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    static Stream<Arguments> badRequestsProvider() {
        return Stream.of(
                Arguments.of(GetBoardsRequest.builder().accountId(" ").build()),
                Arguments.of(GetBoardsRequest.builder().build())
        );
    }
}
