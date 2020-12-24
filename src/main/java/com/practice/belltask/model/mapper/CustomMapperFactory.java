package com.practice.belltask.model.mapper;

import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.office.OfficeSaveDto;
import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;
import com.practice.belltask.model.Office;
import com.practice.belltask.model.Organization;
import com.practice.belltask.model.User;
import com.practice.belltask.view.user.UserIdView;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {


    @Override
    public MapperFactory getObject() {
        MapperFactory factory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        factory.classMap(Office.class, OfficeSaveDto.class)
                .field("organization.id", "orgId")
                .byDefault()
                .register();
        factory.classMap(User.class, UserCreateDto.class)
                .field("office.id", "officeId")
                .field("document.typeDocument.code", "docCode")
                .field("document.typeDocument.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("citizenship.code", "citizenshipCode")
                .byDefault()
                .register();
        factory.classMap(User.class, UserUpdateDto.class)
                .field("office.id", "officeId")
                .field("document.typeDocument.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("citizenship.code", "citizenshipCode")
                .byDefault()
                .register();
        factory.classMap(User.class, UserIdView.class)
                .field("document.typeDocument.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("citizenship.name", "citizenshipName")
                .field("citizenship.code", "citizenshipCode")
                .byDefault()
                .register();
        return factory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}