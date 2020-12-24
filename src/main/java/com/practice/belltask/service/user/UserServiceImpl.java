package com.practice.belltask.service.user;

import com.practice.belltask.controller.advice.NotFoundException;
import com.practice.belltask.dao.User.UserDao;

import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;

import com.practice.belltask.model.User;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.user.UserIdView;
import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import java.util.List;

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
        return mapperFacade.map(dao.get(id), UserIdView.class);
    }

    @Override
    public List<UserListOutView> filter(UserListInView filter) {
        List<User> usrS = dao.getListUser(filter.officeId, filter.firstName, filter.secondName,
                filter.middleName, filter.position, filter.citizenshipCode, filter.docCode);
        if (usrS.isEmpty()) {
            throw new EntityNotFoundException();
        } else
            return mapperFacade.mapAsList(usrS, UserListOutView.class);
    }

    @Transactional
    @Override
    public void save(UserCreateDto dto) {
        if (dto.officeId == null) {
            throw new EntityNotFoundException("officeId must have param");
        }
        dao.save(mapperFacade.map(dto, User.class));
    }

    @Transactional
    @Override
    public void update(UserUpdateDto UpdateDto) {
        dao.update(mapperFacade.map(UpdateDto, User.class));
    }
}

