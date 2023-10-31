package os.psy.research.spotlight.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.service.FocusUnitService;
import os.psy.research.spotlight.presentation.controller.FocusUnitController;
import os.psy.research.spotlight.presentation.dto.BreakTimeDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;
import os.psy.research.spotlight.presentation.dto.request.GetFocusUnitsRequest;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;
import os.psy.research.spotlight.presentation.mapper.FocusUnitMapperImpl;
import os.psy.research.spotlight.presentation.mapper.InterruptionMapper;
import os.psy.research.spotlight.presentation.mapper.WorkingTimeMapper;
import os.psy.research.spotlight.testDataFactory.EntityObjectMother;
import os.psy.research.spotlight.testDataFactory.RequestObjectMother;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FocusUnitController.class)
@ComponentScan(basePackageClasses = {FocusUnitMapperImpl.class, WorkingTimeMapper.class, InterruptionMapper.class})
public class FocusUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FocusUnitService mockService;

    @Nested
    @DisplayName("Test cases for getFocusUnits endpoint")
    class GetFocusUnitsTestCases {
        public final String url = "/rest/os.psy.research.spotlight.v1.FocusUnitApi/units";

        @Test
        void whenGetBoardRequestPassValidationRulesAndMediaTypesThenReturns200() throws Exception {
            var userId = "user-uuid";
            var captor = ArgumentCaptor.forClass(String.class);
            var mockResponse = EntityObjectMother.complete().build();
            var content = mapper.writeValueAsString(GetFocusUnitsRequest.builder().userId(userId).build());
            var request = get(url).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(content);
            when(mockService.getFocusUnits(userId)).thenReturn(List.of(mockResponse));

            mockMvc.perform(request).andExpect(status().isOk());

            verify(mockService, times(1)).getFocusUnits(captor.capture());
            Assertions.assertEquals(userId, captor.getValue());
        }

        @ParameterizedTest
        @MethodSource("badRequestsProvider")
        void whenIncomingRequestFieldsDoNotPassValidationRulesThenReturns400(GetFocusUnitsRequest content) throws Exception {
            var json = mapper.writeValueAsString(content);
            var request = get(url).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

            mockMvc.perform(request).andExpect(status().isBadRequest());
        }

        static Stream<Arguments> badRequestsProvider() {
            return Stream.of(
                    Arguments.of(GetFocusUnitsRequest.builder().userId(" ").build()),
                    Arguments.of(GetFocusUnitsRequest.builder().build())
            );
        }
    }

    @Nested
    @DisplayName("Test cases for registerFocusUnit endpoint")
    class RegisterFocusUnitTestCases {

        public final String url = "/rest/os.psy.research.spotlight.v1.FocusUnitApi/unit";

        @Test
        void whenValidInput_thenReturns200() throws Exception {
            var content = RequestObjectMother.RegisterFocusUnit.complete().build();
            var captor = ArgumentCaptor.forClass(FocusUnit.class);
            var json = mapper.writeValueAsString(content);
            var request = post(url).contentType(MediaType.APPLICATION_JSON).content(json);

            mockMvc.perform(request).andExpect(status().isOk());

            verify(mockService, times(1)).registerFocusUnit(captor.capture());
            Assertions.assertNull(captor.getValue().getEntityId());
            Assertions.assertEquals(content.userId(), captor.getValue().getUserId());
            Assertions.assertEquals(content.linkedTaskId(), captor.getValue().getLinkedTaskId());

            Assertions.assertTrue(content.workingTimeDto().startedAt().isEqual(captor.getValue().getWorkingTime().getStartedAt()));
            Assertions.assertTrue(content.workingTimeDto().completedAt().isEqual(captor.getValue().getWorkingTime().getCompletedAt()));
            Assertions.assertEquals(content.workingTimeDto().plannedMinutes(), captor.getValue().getWorkingTime().getPlannedMinutes());
            Assertions.assertEquals(content.workingTimeDto().interruptionsDto().get(0).type(), captor.getValue().getWorkingTime().getInterruptions().get(0).getType());
            Assertions.assertEquals(content.workingTimeDto().interruptionsDto().get(0).reason(), captor.getValue().getWorkingTime().getInterruptions().get(0).getReason());
            Assertions.assertEquals(content.workingTimeDto().interruptionsDto().get(0).recordedAt(), captor.getValue().getWorkingTime().getInterruptions().get(0).getRecordedAt());

            Assertions.assertTrue(content.breakTimeDto().startedAt().isEqual(captor.getValue().getBreakTime().getStartedAt()));
            Assertions.assertTrue(content.breakTimeDto().completedAt().isEqual(captor.getValue().getBreakTime().getCompletedAt()));
            Assertions.assertEquals(content.breakTimeDto().plannedMinutes(), captor.getValue().getBreakTime().getPlannedMinutes());
            Assertions.assertEquals(content.breakTimeDto().interruptionsDto().get(0).type(), captor.getValue().getBreakTime().getInterruptions().get(0).getType());
            Assertions.assertEquals(content.breakTimeDto().interruptionsDto().get(0).reason(), captor.getValue().getBreakTime().getInterruptions().get(0).getReason());
            Assertions.assertEquals(content.breakTimeDto().interruptionsDto().get(0).recordedAt(), captor.getValue().getBreakTime().getInterruptions().get(0).getRecordedAt());

            Assertions.assertEquals(content.userAssessmentDto().mood(), captor.getValue().getUserAssessment().getMood());
            Assertions.assertEquals(content.userAssessmentDto().feedback(), captor.getValue().getUserAssessment().getFeedback());
        }

        @ParameterizedTest
        @MethodSource("badRequestsProvider")
        void whenIncomingRequestFieldsDoNotPassValidationRulesThenReturns400(RegisterFocusUnitRequest content) throws Exception {
            var json = mapper.writeValueAsString(content);
            var request = post(url).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(json);

            mockMvc.perform(request).andExpect(status().isBadRequest());
        }

        static Stream<Arguments> badRequestsProvider() {
            return Stream.of(
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().userId(" ").build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().userId(null).build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().linkedTaskId(" ").build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().linkedTaskId(null).build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().workingTimeDto(null).build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().workingTimeDto(WorkingTimeDto.builder().build()).build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().breakTimeDto(null).build()),
                    Arguments.of(RequestObjectMother.RegisterFocusUnit.complete().breakTimeDto(BreakTimeDto.builder().build()).build())
            );
        }
    }
}
