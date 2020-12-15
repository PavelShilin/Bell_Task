package com.practice.belltask.service.user;

import com.practice.belltask.dao.User.UserDao;
import com.practice.belltask.model.Document;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.user.UserIdView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao dao, MapperFacade mapperFacade) {
        this.dao = dao;

        this.mapperFacade = mapperFacade;
    }

    @Override
    public UserIdView getUser(Integer id) {
        return dao.getUserId(id);
    }

}

