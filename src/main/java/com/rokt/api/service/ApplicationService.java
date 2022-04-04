package com.rokt.api.service;

import com.rokt.api.dto.RequestDto;
import com.rokt.api.dto.ResponseDto;

import java.util.List;

public interface ApplicationService {

    List<ResponseDto> serveRequest(RequestDto requestDto);
}
