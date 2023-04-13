package com.example.springdatajpa.controllers;

import com.example.springdatajpa.entities.Classes;
import com.example.springdatajpa.services.ClassesService;
import com.example.springdatajpa.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(WebController.class)
@AutoConfigureJsonTesters
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Classes> classesJacksonTester;

    @MockBean
    private ClassesService classesService;

    @MockBean
    private UserService userService;

    public Classes createTestClasses(int id, String name) {
        return Classes.builder().id(id).name(name).build();
    }

    @Test
    public void should_return_classes_by_id_when_get_classes_by_id_and_user_exists() throws Exception {
        final int id = 1;
        final String name = "aaa";
        Classes testClasses = createTestClasses(id, name);
        when(classesService.getClassesById(id)).thenReturn(testClasses);
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/classes/info")
                        .queryParam("id", String.valueOf(id)
                        )
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        ;

        MvcResult mvcResult = resultActions.andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        assertEquals(json, classesJacksonTester.write(testClasses).getJson());

        verify(classesService).getClassesById(id);
    }

}
