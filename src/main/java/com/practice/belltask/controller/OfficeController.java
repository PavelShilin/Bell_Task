package com.practice.belltask.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.belltask.dto.ResponseDto;
import com.practice.belltask.dto.SuccessResponseDto;
import com.practice.belltask.dto.office.OfficeSaveDto;
import com.practice.belltask.dto.office.OfficeUpdateDto;
import com.practice.belltask.service.office.OfficeService;
import com.practice.belltask.view.office.OfficeIdView;
import com.practice.belltask.view.office.OfficeListInView;
import com.practice.belltask.view.office.OfficeListOutView;
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
    public ResponseEntity<ResponseDto<List<OfficeListOutView>>> list(@RequestBody @Valid OfficeListInView filter, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        return dataResponse(service.filter(filter));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<OfficeIdView>> get(@PathVariable Integer id) {
        OfficeIdView office = service.getOfficeById(id);
        if (office == null) return errorResponse("office not found", HttpStatus.BAD_GATEWAY);
        return dataResponse(office);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<SuccessResponseDto>> save(@RequestBody @Valid OfficeSaveDto dto, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        service.save(dto);
        return successResponse();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<SuccessResponseDto>> update(@RequestBody @Valid OfficeUpdateDto dto, BindingResult binding) {
        if (binding.hasErrors()) {
            return errorResponse(binding);
        }
        service.update(dto);
        return successResponse();
    }
}

