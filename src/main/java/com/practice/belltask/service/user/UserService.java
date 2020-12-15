package com.practice.belltask.service.user;

import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.model.Document;
import com.practice.belltask.model.User;
import com.practice.belltask.view.user.UserIdView;
import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {

    UserIdView getUser(Integer id);

    List<UserListOutView> filter(UserListInView filter);

    void save(UserCreateDto dto);

}
