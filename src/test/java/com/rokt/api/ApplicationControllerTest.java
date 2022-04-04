package com.rokt.api;

import com.rokt.api.dto.RequestDto;
import com.rokt.api.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ApplicationControllerTest {

    @Test
    public void ApplicationController_getRequest_Test() {
        ApplicationService applicationService = Mockito.mock(ApplicationService.class);
        RequestDto requestDto = new RequestDto();
        requestDto.setFilename("sample1.txt");
        requestDto.setFrom("2000-01-01T13:38:29Z");
        requestDto.setTo("2001-12-07T23:00:00Z");

        ApplicationController applicationController = new ApplicationController(applicationService);
        applicationController.getRequest(requestDto);

        Mockito.verify(applicationService).serveRequest(requestDto);
    }
}
