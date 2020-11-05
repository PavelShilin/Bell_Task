package com.practice.BellTask.controller;

import com.practice.BellTask.model.Organization;
import com.practice.BellTask.service.OrganizationService;
import com.practice.BellTask.view.OrganizationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<OrganizationView> organizationsList() {
        return organizationServ.organizations();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Organization> customOrganization(@PathVariable("id") Integer id) {
        return new ResponseEntity<Organization>(organizationServ.getOrgById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить организацию", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/save")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void addOrganization(@RequestBody  Organization org) {
        OrganizationView orgNew = new OrganizationView();
        orgNew.address= org.getAddress();
        orgNew.status = org.isStatus();
        orgNew.name = org.getName();
        orgNew.fullName = org.getFullName();
        orgNew.inn = org.getInn();
        orgNew.kpp = org.getKpp();
        orgNew.phone = org.getPhone();
        organizationServ.add(orgNew);
    }

}
