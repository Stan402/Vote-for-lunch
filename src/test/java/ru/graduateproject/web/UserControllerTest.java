package ru.graduateproject.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.graduateproject.TestUtil;
import ru.graduateproject.UserTestData;
import ru.graduateproject.model.User;
import ru.graduateproject.service.UserService;
import ru.graduateproject.web.json.JsonUtil;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.graduateproject.TestUtil.*;
import static ru.graduateproject.UserTestData.*;
import static ru.graduateproject.web.json.JsonUtil.*;

public class UserControllerTest extends AbstractControllerTest{

    private static final String REST_URL = UserController.REST_URL + '/';

    @Autowired
    UserService service;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(USER, USER1, ADMIN));

    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    public void createWithLocation() throws Exception {
        User expected = new User(NEW_USER);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(expected)))
                .andExpect(status().isCreated());
        User returned = TestUtil.readFromJson(action, User.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(service.getAll(), USER, USER1, ADMIN, expected);
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), USER1, ADMIN);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isOk());
        assertMatch(service.getAll(), updated, USER1, ADMIN);

    }

}