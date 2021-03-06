package com.rokt.api.service;

import com.rokt.api.dto.RequestDto;
import com.rokt.api.dto.ResponseDto;
import com.rokt.api.entity.TextRecordEntity;
import com.rokt.api.util.Transformer;
import com.rokt.api.util.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationImplementation implements ApplicationService {

    @Value(value = "${app.test-files.directory:/app/test-files}")
    private String applicationTestFilesDirectory;

    private final Transformer transformer;
    private final Validator validator;

    public ApplicationImplementation(Transformer transformer, Validator validator) {
        this.transformer = transformer;
        this.validator = validator;
    }

    public List<ResponseDto> serveRequest(RequestDto requestDto) {
        File file = new File(applicationTestFilesDirectory);
        File[] fileList = file.listFiles();
        File textRecordFile = fileList != null ? Arrays.stream(fileList)
                .filter(f -> f.getName().equals(requestDto.getFilename()))
                .findFirst()
                .orElse(null) : null;

        return textRecordFile != null && requestDtoIsValid(requestDto) ?
                createTextRecordResponse(requestDto, textRecordFile) :
                new ArrayList<>();
    }

    private boolean requestDtoIsValid(RequestDto requestDto) {
        return validator.isISOFormat(requestDto.getFrom()) && validator.isISOFormat(requestDto.getTo());
    }

    private List<ResponseDto> createTextRecordResponse(RequestDto requestDto, File file) {
        List<ResponseDto> responseDtoList = new ArrayList<>();
        LocalDateTime startDateTime = transformer.toLocalDateTime(requestDto.getFrom());
        LocalDateTime endDateTime = transformer.toLocalDateTime(requestDto.getTo());
        try {
            if (validator.isValidDateRange(startDateTime, endDateTime)) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    TextRecordEntity textRecordEntity = transformer.toTextRecordEntity(line);
                    if (dateIsContainedWithinRange(textRecordEntity.getDate(), startDateTime, endDateTime)) {
                        responseDtoList.add(transformer.toResponseDto(textRecordEntity));
                    }
                    // Short circuit reading the file if dates after endDateTime is reached
                    else if (textRecordEntity.getDate().isAfter(endDateTime)) {
                        break;
                    }
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseDtoList;
    }

    private boolean dateIsContainedWithinRange(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return target.equals(start) || target.equals(end) || (target.isAfter(start) && target.isBefore(end));
    }
}
