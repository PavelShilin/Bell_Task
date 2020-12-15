package com.practice.belltask.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.SuccessResponseDto;
import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.service.user.UserService;
import com.practice.belltask.view.user.UserIdView;
import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.practice.belltask.util.ResponseUtil.*;
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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<List<UserListOutView>>> list(@RequestBody @Valid UserListInView filter, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        return dataResponse(service.filter(filter));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<SuccessResponseDto>> save(@RequestBody @Valid UserCreateDto dto, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        service.save(dto);
        return successResponse();
    }


}
