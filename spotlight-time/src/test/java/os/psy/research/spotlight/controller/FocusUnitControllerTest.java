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

import java.util.Collections;
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
    private FocusUnitService underTest;

    @Nested
    @DisplayName("Test cases for getFocusUnits endpoint")
    class GetFocusUnitsTestCases {
        public final String url = "/rest/os.psy.research.spotlight.v1.FocusUnitApi/units";

        @Test
        void whenValidInput_thenReturns200() throws Exception {
            var userId = "user-uuid";
            var request = GetFocusUnitsRequest.builder().userId(userId).build();
            var captor = ArgumentCaptor.forClass(String.class);
            var unit = EntityObjectMother.complete().build();
            when(underTest.getFocusUnits(userId)).thenReturn(Collections.singletonList(unit));

            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isOk());

            verify(underTest, times(1)).getFocusUnits(captor.capture());
            Assertions.assertEquals(userId, captor.getValue());
        }

        static Stream<Arguments> badRequestProvider() {
            return Stream.of(
                    Arguments.of(GetFocusUnitsRequest.builder().userId(" ").build()),
                    Arguments.of(GetFocusUnitsRequest.builder().build())
            );
        }

        @ParameterizedTest
        @MethodSource("badRequestProvider")
        void whenIncomingRequestFieldsDoNotPassValidationRulesBadRequestIsReturned(GetFocusUnitsRequest request) throws Exception {
            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenInvalidInput_thenReturns400() throws Exception {
            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenInvalidContentType_thenReturns415() throws Exception {
            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_ATOM_XML))
                    .andExpect(status().isUnsupportedMediaType());
        }

        @Test
        void whenInvalidAcceptableType_thenReturns406() throws Exception {
            mockMvc.perform(get(url).accept(MediaType.APPLICATION_ATOM_XML)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotAcceptable());
        }
    }

    @Nested
    @DisplayName("Test cases for registerFocusUnit endpoint")
    class RegisterFocusUnitTestCases {

        public final String url = "/rest/os.psy.research.spotlight.v1.FocusUnitApi/unit";

        @Test
        void whenValidInput_thenReturns200() throws Exception {
            var request = RequestObjectMother.RegisterFocusUnit.complete().build();
            var captor = ArgumentCaptor.forClass(FocusUnit.class);

            mockMvc.perform(post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isOk());

            verify(underTest, times(1)).registerFocusUnit(captor.capture());
            Assertions.assertNull(captor.getValue().getEntityId());
            Assertions.assertEquals(request.userId(), captor.getValue().getUserId());
            Assertions.assertEquals(request.linkedTaskId(), captor.getValue().getLinkedTaskId());

            Assertions.assertTrue(request.workingTimeDto().startedAt().isEqual(captor.getValue().getWorkingTime().getStartedAt()));
            Assertions.assertTrue(request.workingTimeDto().completedAt().isEqual(captor.getValue().getWorkingTime().getCompletedAt()));
            Assertions.assertEquals(request.workingTimeDto().plannedMinutes(), captor.getValue().getWorkingTime().getPlannedMinutes());
            Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).type(), captor.getValue().getWorkingTime().getInterruptions().get(0).getType());
            Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).reason(), captor.getValue().getWorkingTime().getInterruptions().get(0).getReason());
            Assertions.assertEquals(request.workingTimeDto().interruptionsDto().get(0).recordedAt(), captor.getValue().getWorkingTime().getInterruptions().get(0).getRecordedAt());

            Assertions.assertTrue(request.breakTimeDto().startedAt().isEqual(captor.getValue().getBreakTime().getStartedAt()));
            Assertions.assertTrue(request.breakTimeDto().completedAt().isEqual(captor.getValue().getBreakTime().getCompletedAt()));
            Assertions.assertEquals(request.breakTimeDto().plannedMinutes(), captor.getValue().getBreakTime().getPlannedMinutes());
            Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).type(), captor.getValue().getBreakTime().getInterruptions().get(0).getType());
            Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).reason(), captor.getValue().getBreakTime().getInterruptions().get(0).getReason());
            Assertions.assertEquals(request.breakTimeDto().interruptionsDto().get(0).recordedAt(), captor.getValue().getBreakTime().getInterruptions().get(0).getRecordedAt());

            Assertions.assertEquals(request.userAssessmentDto().mood(), captor.getValue().getUserAssessment().getMood());
            Assertions.assertEquals(request.userAssessmentDto().feedback(), captor.getValue().getUserAssessment().getFeedback());
        }

        static Stream<Arguments> badRequestProvider() {
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

        @ParameterizedTest
        @MethodSource("badRequestProvider")
        void whenIncomingRequestFieldsDoNotPassValidationRulesBadRequestIsReturned(RegisterFocusUnitRequest request) throws Exception {
            mockMvc.perform(post(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenInvalidInput_thenReturns400() throws Exception {
            mockMvc.perform(post(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenInvalidContentType_thenReturns415() throws Exception {
            mockMvc.perform(post(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_ATOM_XML))
                    .andExpect(status().isUnsupportedMediaType());
        }

        @Test
        void whenInvalidAcceptableType_thenReturns406() throws Exception {
            mockMvc.perform(post(url).accept(MediaType.APPLICATION_ATOM_XML)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotAcceptable());
        }

    }
}
