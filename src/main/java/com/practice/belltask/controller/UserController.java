package com.practice.belltask.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.service.user.UserService;
import com.practice.belltask.view.user.UserIdView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.practice.belltask.util.ResponseUtil.dataResponse;
import static com.practice.belltask.util.ResponseUtil.errorResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<UserIdView>> get(@PathVariable int id) {
        UserIdView user = service.getUser(id);
        if (user == null) return errorResponse("User not found", HttpStatus.NOT_FOUND);
        return dataResponse(user);
    }

}
