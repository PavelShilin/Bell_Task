package com.practice.BellTask.controller;

import com.practice.BellTask.model.Organization;
import com.practice.BellTask.service.OrganizationService;
import com.practice.BellTask.view.OrganizationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationServ;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationServ = organizationService;
    }

    @ApiOperation(value = "Получить список всех людей", httpMethod = "GET")
    @GetMapping("/list")
    public List<OrganizationView> organizations() {
        return organizationServ.organizations();
    }

    @ApiOperation(value = "Добавить организацию", httpMethod = "post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void customOrganizations(@RequestBody OrganizationView org) {
        organizationServ.add(org);
    }

}
