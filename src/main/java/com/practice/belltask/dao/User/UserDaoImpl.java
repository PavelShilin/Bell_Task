package com.practice.belltask.dao.User;

import com.practice.belltask.controller.advice.NotFoundException;
import com.practice.belltask.dao.citizenship.CitizenshipDao;
import com.practice.belltask.dao.document.DocumentDao;
import com.practice.belltask.dao.office.OfficeDao;
import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;
import com.practice.belltask.model.*;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.user.UserIdView;

import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;
    private final MapperFacade mapperFacade;
    private final OfficeDao officeDao;
    private final DocumentDao documentDao;
    private final CitizenshipDao citizenshipDao;

    @Autowired
    public UserDaoImpl(EntityManager em, MapperFacade mapperFacade, OfficeDao officeDao, DocumentDao documentDao, CitizenshipDao citizenshipDao) {
        this.em = em;
        this.mapperFacade = mapperFacade;
        this.officeDao = officeDao;
        this.documentDao = documentDao;
        this.citizenshipDao = citizenshipDao;
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
        userView.setDocName(document.getTypeDocument().getName());
        userView.setDocNumber(document.getDocNumber());
        userView.setDocDate(document.getDocDate());
        userView.setCitizenshipCode(citizenship.getCode());
        userView.setCitizenshipName(citizenship.getName());
        return userView;
    }

    @Override
    public List<UserListOutView> getListUser(UserListInView userList) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Root<Document> documentRoot = criteriaQuery.from(Document.class);
        criteriaQuery.select(userRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(userRoot.get("office").get("id"), userList.getOfficeId()));
        if (userList.firstName != null) {
            predicates.add(cb.equal(userRoot.get("firstName"), userList.firstName));
        }
        if (userList.secondName != null) {
            predicates.add(cb.equal(userRoot.get("secondName"), userList.secondName));
        }
        if (userList.middleName != null) {
            predicates.add(cb.equal(userRoot.get("middleName"), userList.middleName));
        }
        if (userList.position != null) {
            predicates.add(cb.equal(userRoot.get("position"), userList.position));
        }
        if (userList.citizenshipCode != null) {
            predicates.add(cb.equal(userRoot.get("citizenship").get("code"), userList.citizenshipCode));
        }
        if (userList.docCode != null) {
            predicates.add(cb.equal(documentRoot.get("id"), userRoot.get("id")));
            predicates.add(cb.equal(documentRoot.get("typeDocument").get("code"), userList.docCode));
        }
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[]{}))).distinct(true);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return mapperFacade.mapAsList(query.getResultList(), UserListOutView.class);
    }

    @Override
    public void save(UserCreateDto userDto) {
        User tempUser = new User();
        tempUser.setFirstName(userDto.getFirstName());
        tempUser.setSecondName(userDto.getSecondName());
        tempUser.setMiddleName(userDto.getMiddleName());
        tempUser.setOffice(officeDao.loadOfficeById(userDto.officeId));
        tempUser.setPosition(userDto.position);
        tempUser.setIsIdentified(userDto.getIsIdentified());
        if (userDto.citizenshipCode != null) {
            tempUser.setCitizenship(citizenshipDao.getCitizenshipByCode(userDto.citizenshipCode));
        }
        em.persist(tempUser);
        //   documentDao.createDocument(tempUser, userDto.docNumber, userDto.docDate, userDto.docName, userDto.docCode);
        if ((userDto.docCode != null || (userDto.docName != null)) &&
                ((userDto.docNumber != null) || (userDto.docDate != null))) {
            documentDao.createDocument(tempUser.getId(), userDto.docNumber, userDto.docDate, userDto.docName, userDto.docCode);
        }
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) {
        if (userUpdateDto.id == null) {
            throw new NotFoundException("id not found");
        }
        User tempUser = em.find(User.class, userUpdateDto.id);
        if (userUpdateDto.getOfficeId() != null) {
            tempUser.setOffice(officeDao.loadOfficeById(userUpdateDto.officeId));
        }
        tempUser.setFirstName(userUpdateDto.getFirstName());
        if (userUpdateDto.getSecondName() != null) {
            tempUser.setSecondName(userUpdateDto.getSecondName());
        }
        if (userUpdateDto.getMiddleName() != null) {
            tempUser.setMiddleName(userUpdateDto.getMiddleName());
        }
        if (userUpdateDto.getPosition() != null) {
            tempUser.setPosition(userUpdateDto.getPosition());
        } else throw new NotFoundException("Position not found ");

        if (userUpdateDto.getPhone() != null) {
            tempUser.setPhone(userUpdateDto.getPhone());
        }
        if (userUpdateDto.getCitizenshipCode() != null) {
            tempUser.setCitizenship(citizenshipDao.getCitizenshipByCode(userUpdateDto.getCitizenshipCode()));
        }
        em.persist(tempUser);

        documentDao.updateDocument(userUpdateDto.id, userUpdateDto.docNumber, userUpdateDto.docDate, userUpdateDto.docName);

    }

}
