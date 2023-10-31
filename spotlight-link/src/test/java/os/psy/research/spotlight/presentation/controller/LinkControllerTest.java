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

    public static final String BOARDS_URL = SPOTLIGHT_V_1_LINK_API + "/boards";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LinkService mockService;
    @Test
    void whenValidInput_thenReturns200() throws Exception {
        var accountId = "acc1";
        var request = GetBoardsRequest.builder().accountId(accountId).build();
        var captor = ArgumentCaptor.forClass(String.class);
        var board = EntityObjectMother.complete().build();
        when(mockService.getAllBoardsForGivenAccount(accountId)).thenReturn(Collections.singletonList(board));

        mockMvc.perform(get(BOARDS_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(mockService, times(1)).getAllBoardsForGivenAccount(captor.capture());
        Assertions.assertEquals(accountId, captor.getValue());
    }

    static Stream<Arguments> badRequestProvider() {
        return Stream.of(
                Arguments.of(GetBoardsRequest.builder().accountId(" ").build()),
                Arguments.of(GetBoardsRequest.builder().build())
        );
    }
    @ParameterizedTest
    @MethodSource("badRequestProvider")
    void whenIncomingRequestFieldsDoNotPassValidationRulesBadRequestIsReturned(GetBoardsRequest request) throws Exception {
        mockMvc.perform(get(BOARDS_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidInput_thenReturns400() throws Exception {
        mockMvc.perform(get(BOARDS_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidContentType_thenReturns415() throws Exception {
        mockMvc.perform(get(BOARDS_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void whenInvalidAcceptableType_thenReturns406() throws Exception {
        mockMvc.perform(get(BOARDS_URL).accept(MediaType.APPLICATION_ATOM_XML)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotAcceptable());
    }
}