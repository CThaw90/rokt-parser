package com.rokt.api.util;

import com.rokt.api.dto.ResponseDto;
import com.rokt.api.entity.TextRecordEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transformer {

    public static LocalDateTime toLocalDateTime(String value) {
        String invalidCharactersRegex = "[^\\x00-\\x7F]";
        return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(
                value.replaceAll(invalidCharactersRegex, ""))
        );
    }

    public static TextRecordEntity toTextRecordEntity(String recordRow) {
        int EVENT_TIME_INDEX = 0, EMAIL_INDEX = 1, SESSION_ID_INDEX = 2;
        String[] rowEntries = recordRow.split("\\s");

        TextRecordEntity textRecordEntity = new TextRecordEntity();
        textRecordEntity.setDate(toLocalDateTime(rowEntries[EVENT_TIME_INDEX]));
        textRecordEntity.setEmail(rowEntries[EMAIL_INDEX]);
        textRecordEntity.setSessionId(rowEntries[SESSION_ID_INDEX]);

        return textRecordEntity;
    }

    public static ResponseDto toResponseDto(TextRecordEntity textRecordEntity) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setEmail(textRecordEntity.getEmail());
        responseDto.setSessionId(textRecordEntity.getSessionId());
        responseDto.setEventTime(textRecordEntity.getDate().toString());

        return responseDto;
    }
}
