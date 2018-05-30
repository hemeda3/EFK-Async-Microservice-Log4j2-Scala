package com.ahmed.business;

import com.ahmed.dto.UserDTO;
import com.ahmed.entities.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface IUserBusiness {

  List<UserDTO> findUser(final String key, final String operation, final String value);

  User saveUser(UserDTO user);
}
