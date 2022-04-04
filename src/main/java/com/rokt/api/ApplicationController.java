package com.rokt.api;

import com.rokt.api.dto.RequestDto;
import com.rokt.api.dto.ResponseDto;
import com.rokt.api.service.ApplicationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<ResponseDto> getRequest(@RequestBody RequestDto requestDto) {
        return applicationService.serveRequest(requestDto);
    }
}
