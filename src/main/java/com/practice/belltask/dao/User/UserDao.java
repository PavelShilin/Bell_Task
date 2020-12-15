package com.practice.belltask.dao.User;

import com.practice.belltask.model.Document;
import com.practice.belltask.model.User;
import com.practice.belltask.view.user.UserIdView;

public interface UserDao {
    User get(Integer id);

    UserIdView getUserId(Integer id);
}
