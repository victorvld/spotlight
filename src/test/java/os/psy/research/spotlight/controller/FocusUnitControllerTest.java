package os.psy.research.spotlight.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.presentation.dto.GetFocusUnitRequest;
import os.psy.research.spotlight.presentation.controller.FocusUnitController;
import os.psy.research.spotlight.domain.service.FocusUnitService;
import os.psy.research.spotlight.presentation.mapper.FocusUnitMapperImpl;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FocusUnitController.class)
@ComponentScan(basePackageClasses = {FocusUnitMapperImpl.class})
public class FocusUnitControllerTest {
    public static final String BASE_URL = "/rest/os.psy.research.spotlight.v1.FocusUnitApi/units";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FocusUnitService underTest;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        var userId = "user-uuid";
        var request = GetFocusUnitRequest.builder().userId(userId).build();
        var units = Collections.singletonList(FocusUnit.builder().userUuid(userId).build());
        when(underTest.getFocusUnits(userId)).thenReturn(units);

        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(underTest, times(1)).getFocusUnits(userId);
    }

    @Test
    void whenBlankUuid_thenReturns400() throws Exception {
        var userId = "";
        var request = GetFocusUnitRequest.builder().userId(userId).build();

        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNullUuid_thenReturns400() throws Exception {
        var request = GetFocusUnitRequest.builder().build();

        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidInput_thenReturns400() throws Exception {
        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidContentType_thenReturns415() throws Exception {
        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void whenInvalidAcceptableType_thenReturns406() throws Exception {
        this.mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_ATOM_XML)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotAcceptable());
    }

}
