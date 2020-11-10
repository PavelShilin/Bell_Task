package com.practice.BellTask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.BellTask.model.Organization;
import com.practice.BellTask.service.OrganizationService;
import com.practice.BellTask.view.organization.OrganizationListInView;
import com.practice.BellTask.view.organization.OrganizationListOutView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationController {

    private final OrganizationService organizationServ;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationServ = organizationService;
    }

    @ApiOperation(value = "лист организаций", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/list")
    public List<OrganizationListOutView> listOrganization(@RequestBody OrganizationListInView org) {
        return organizationServ.getOrgByName(org.name);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Organization> customOrganization(@PathVariable("id") Integer id) {
        return new ResponseEntity<Organization>(organizationServ.getOrgById(id), HttpStatus.OK);
    }


}
