package com.practice.belltask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.SuccessResponseDto;
import com.practice.belltask.dto.organization.OrganizationSaveDto;
import com.practice.belltask.dto.organization.OrganizationUpdateDto;
import com.practice.belltask.service.organization.OrganizationService;
import com.practice.belltask.view.organization.OrganizationIdView;
import com.practice.belltask.view.organization.OrganizationListInView;
import com.practice.belltask.view.organization.OrganizationListOutView;
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

@Api(value = "OrganizationController")
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<List<@Valid OrganizationListOutView>>> list(@RequestBody @Valid OrganizationListInView org, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        } else
            return dataResponse(service.filter(org));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<OrganizationIdView>> get(@PathVariable int id) {
        OrganizationIdView org = service.getId(id);
        if (org == null) return errorResponse("Organization not found", HttpStatus.BAD_GATEWAY);
        return dataResponse(org);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<SuccessResponseDto>> save(@RequestBody @Valid OrganizationSaveDto dto, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        service.save(dto);
        return successResponse();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<SuccessResponseDto>> update(@RequestBody @Valid OrganizationUpdateDto dto, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        service.update(dto);
        return successResponse();
    }


}
