package psy.research.spotlight.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class FocusUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getFocusUnitTest() throws Exception {
        var uri = "/units";
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        var status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
