package com.practice.belltask.dao.User;

import com.practice.belltask.model.*;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.user.UserIdView;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;


@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;
    private final MapperFacade mapperFacade;

    public UserDaoImpl(EntityManager em, MapperFacade mapperFacade) {
        this.em = em;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public User get(Integer id) {
        return em.find(User.class, id, LockModeType.NONE);
    }

    @Override
    public UserIdView getUserId(Integer id) {
        User user = em.find(User.class, id);
        Citizenship citizenship = user.getCitizenship();
        Document document = em.find(Document.class, id);
        UserIdView userView = mapperFacade.map(user, UserIdView.class);
        userView.setDocName(document.getTypeDocument().getNameTypeDocument());
        userView.setDocNumber(document.getDocNumber());
        userView.setDocDate(document.getDocDate());
        userView.setCitizenshipCode(citizenship.getCodeCitizenship());
        userView.setCitizenshipName(citizenship.getNameCitizenship());
        return userView;
    }


}
