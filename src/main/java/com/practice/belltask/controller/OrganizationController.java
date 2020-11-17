package com.practice.belltask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.model.Organization;
import com.practice.belltask.service.OrganizationService;
import com.practice.belltask.view.organization.OrganizationListInView;
import com.practice.belltask.view.organization.OrganizationListOutView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "лист организаций", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/list")
    public List<OrganizationListOutView> listOrganization(@RequestBody OrganizationListInView org) {
        return organizationService.getOrgByName(org.name);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Organization> customOrganization(@PathVariable("id") Integer id) {
        return new ResponseEntity<Organization>(organizationService.getOrgById(id), HttpStatus.OK);
    }


}
