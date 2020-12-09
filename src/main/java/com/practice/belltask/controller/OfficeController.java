package com.practice.belltask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.service.office.OfficeService;
import com.practice.belltask.view.office.OfficeIdView;
import com.practice.belltask.view.office.OfficeListInView;
import com.practice.belltask.view.office.OfficeListOutView;
import com.practice.belltask.view.organization.OrganizationIdView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.practice.belltask.util.ResponseUtil.*;

import javax.validation.Valid;

import java.util.List;

import static com.practice.belltask.util.ResponseUtil.dataResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController")
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<List<OfficeListOutView>>> list (@RequestBody @Valid OfficeListInView filter, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        return dataResponse(service.filter(filter));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<OfficeIdView>> get(@PathVariable Integer id) {
        OfficeIdView office = service.getOfficeById(id);
        if (office == null) return errorResponse("office not found", HttpStatus.BAD_GATEWAY);
        System.out.println(office.address+"&&&&&&&&&&&&&&&&&&&&");
        return dataResponse(office);
    }
}
