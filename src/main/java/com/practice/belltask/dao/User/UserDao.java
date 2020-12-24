package com.practice.belltask.dao.User;

import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;
import com.practice.belltask.model.Document;
import com.practice.belltask.model.User;
import com.practice.belltask.view.user.UserIdView;
import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;

import java.util.List;

public interface UserDao {

    User get(Integer id);

    List<User> getListUser(Integer officeId, String firstName, String secondName, String middleName,
                           String position, Integer citizenshipCode, Integer docCode);

    void save(User user);

    void update(User user);

}
