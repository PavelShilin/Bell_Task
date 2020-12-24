package com.practice.belltask.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.SuccessResponseDto;
import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;
import com.practice.belltask.service.user.UserService;
import com.practice.belltask.view.user.UserIdView;
import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    public UserIdView get(@PathVariable int id) {
        return service.getUser(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserIdView user(@PathVariable(name = "id") Integer id) {
        return service.getUser(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<UserListOutView> list(@RequestBody @Valid UserListInView filter) {
        return service.filter(filter);
    }

    @ApiOperation(value = "Добавить нового работника", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void saveUser(@Valid
                         @RequestBody UserCreateDto user) {
        service.save(user);
    }

    @ApiOperation(value = "Обновить юзера", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void updateOffice(@Valid
                             @RequestBody UserUpdateDto user) {
        service.update(user);
    }


}
