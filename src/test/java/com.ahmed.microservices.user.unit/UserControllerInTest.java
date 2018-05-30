package com.ahmed.microservices.user.unit;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ahmed.UserApplication;
import com.ahmed.business.IUserBusiness;
import com.ahmed.business.UserBusinessImp;
import com.ahmed.dto.UserDTO;
import com.ahmed.entities.User;
import com.ahmed.enums.StatfloErrors;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@AutoConfigureMockMvc
public class UserControllerInTest {

  @Autowired private MockMvc mockMvc;

  private IUserBusiness userBusiness;
  private ObjectMapper mapper;

  @Before
  public void setUp() {

    mapper = new ObjectMapper();
    userBusiness = mock(UserBusinessImp.class);
    Mockito.reset(userBusiness);
  }

  @Test
  public void getUser() throws Exception {
    UserDTO userDTO = UserDTO.builder().name("john").role("foo").build();
    when(userBusiness.findUser(anyString(), anyString(), anyString()))
        .thenReturn(Collections.singletonList(userDTO));
    mockMvc
        .perform(get("/users/?role=foo"))
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$[0].name", is("john")))
        .andExpect(jsonPath("$[0].role", is("foo")));

    verifyZeroInteractions(userBusiness);
  }

  // @Test
  public void add_NewUSEREntry_ShouldAddUserEntryAndReturnAddedEntry() throws Exception {
    User user = User.builder().name("jodi").role("foo").build();

    String dtoAsString = mapper.writeValueAsString(user);

    when(userBusiness.saveUser(any(UserDTO.class))).thenReturn(user);

    mockMvc
        .perform(post("/users/").contentType(TestUtil.APPLICATION_JSON_UTF8).content(dtoAsString))
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name", is("jodi")))
        .andExpect(jsonPath("$.role", is("foo")));

    verifyZeroInteractions(userBusiness);
  }

  @Test
  public void saveEmptyUserValidationShouldReturnValidationException() throws Exception {

    mockMvc
        .perform(post("/users/").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.errorCode", is(StatfloErrors.INPUT_VALIDATION.getCode())))
        .andExpect(jsonPath("$.errorMessage", is(StatfloErrors.INPUT_VALIDATION.getDescription())));
  }
}
