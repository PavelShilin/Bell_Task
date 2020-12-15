package com.practice.belltask.service.user;

import com.practice.belltask.model.Document;
import com.practice.belltask.model.User;
import com.practice.belltask.view.user.UserIdView;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    UserIdView getUser(Integer id);


}
