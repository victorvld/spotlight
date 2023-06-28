package os.psy.research.spotlight.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.request.GetFocusUnitsRequest;
import os.psy.research.spotlight.presentation.mapper.FocusUnitMapperImpl;
import os.psy.research.spotlight.presentation.mapper.LinkedResourceMapperImpl;
import os.psy.research.spotlight.testDataFactory.FocusUnitDtoMother;
import os.psy.research.spotlight.testDataFactory.FocusUnitMother;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FocusUnitController.class)
@ComponentScan(basePackageClasses = {FocusUnitMapperImpl.class, LinkedResourceMapperImpl.class})
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
            var unit = FocusUnitMother.complete().build();
            when(underTest.getFocusUnits(userId)).thenReturn(Collections.singletonList(unit));

            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isOk());

            verify(underTest, times(1)).getFocusUnits(captor.capture());
            Assertions.assertEquals(userId, captor.getValue());
        }

        @Test
        void whenBlankUuid_thenReturns400() throws Exception {
            var userId = "";
            var request = GetFocusUnitsRequest.builder().userId(userId).build();

            mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenNullUuid_thenReturns400() throws Exception {
            var request = GetFocusUnitsRequest.builder().build();

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
            var request = FocusUnitDtoMother.complete().build();
            var captor = ArgumentCaptor.forClass(FocusUnit.class);

            mockMvc.perform(post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isOk());

            verify(underTest, times(1)).registerFocusUnit(captor.capture());
            Assertions.assertNull(captor.getValue().getEntityId());
            Assertions.assertEquals(request.userId(), captor.getValue().getUserId());
            Assertions.assertEquals(request.linkedResourceDto().projectId(), captor.getValue().getLinkedResource().getProjectId());
            Assertions.assertEquals(request.linkedResourceDto().taskId(), captor.getValue().getLinkedResource().getTaskId());
            Assertions.assertTrue(request.workingTimeDto().startedAt().isEqual(captor.getValue().getWorkingTime().startedAt()));
            Assertions.assertTrue(request.workingTimeDto().completedAt().isEqual(captor.getValue().getWorkingTime().completedAt()));
            Assertions.assertEquals(request.workingTimeDto().selectedDuration(), captor.getValue().getWorkingTime().selectedDuration());
        }


        @Test
        void whenBlankUuid_thenReturns400() throws Exception {
            var userId = "";
            var request = FocusUnitDto.builder().userId(userId).build();

            mockMvc.perform(post(url).accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void whenNullUuid_thenReturns400() throws Exception {
            var request = FocusUnitDto.builder().build();

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
