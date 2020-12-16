package com.practice.belltask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dao.reference.ReferenceDao;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.reference.CitizenshipDto;
import com.practice.belltask.dto.reference.DocumentDto;
import com.practice.belltask.view.organization.OrganizationListInView;
import com.practice.belltask.view.organization.OrganizationListOutView;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.practice.belltask.util.ResponseUtil.dataResponse;
import static com.practice.belltask.util.ResponseUtil.errorResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController")
@RestController
@RequestMapping(value = "/api/", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceController {

    private final ReferenceDao dao;

    public ReferenceController(ReferenceDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/docs", method = RequestMethod.GET)
    public List<DocumentDto> docs() {
        return dao.getListDocument();
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<CitizenshipDto> countries() {
        return (dao.getListCitizenship());
    }


}
