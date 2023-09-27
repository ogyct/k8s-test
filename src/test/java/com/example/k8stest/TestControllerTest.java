package com.example.k8stest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


class TestControllerTest extends IntegrationTest {
    @Value("${info.app.version:unknown}")
    private String version;

    @Test
    void shouldReturnNonOverriddenProp() throws Exception {
        mockMvc.perform(get("/property"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("not changed"))
        ;
    }

    @Test
    void shouldReturnAppVersion() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(version))
        ;
    }
}
