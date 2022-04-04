package com.rokt.api.util;

import com.rokt.api.dto.ResponseDto;
import com.rokt.api.entity.TextRecordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransformerTest {

    @Test
    public void Transformer_toLocalDateTime_Test() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 7, 6, 23, 0, 0);
        Assertions.assertEquals(Transformer.toLocalDateTime("2021-07-06T23:00:00Z"), localDateTime);

        localDateTime = LocalDateTime.of(2000, 1, 28, 23, 49, 35);
        Assertions.assertNotEquals(Transformer.toLocalDateTime("2000-01-28T21:49:35Z"), localDateTime);
    }

    @Test
    public void Transformer_toTextRecordEntity_Test() {
        String recordRowBriana = "2000-01-06T07:08:57Z briana_deckow@kihnkessler.us 0ef7675c-738d-4528-80d3-3d13aef7c724";
        String recordRowJarret = "2000-01-06T07:46:21Z jarret_dooley@champlin.info fa80d413-3332-4cc7-af3b-82e52b60cfdb";
        String recordRowEzra = "2000-01-06T12:49:17Z ezra.cartwright@bradtke.biz 03242e3f-c10a-47b9-93a0-1adace85c27c";

        TextRecordEntity textRecordEntityBriana = Transformer.toTextRecordEntity(recordRowBriana);
        TextRecordEntity textRecordEntityJarret = Transformer.toTextRecordEntity(recordRowJarret);
        TextRecordEntity textRecordEntityEzra = Transformer.toTextRecordEntity(recordRowEzra);

        Assertions.assertEquals(textRecordEntityBriana.getDate(), LocalDateTime.of(2000, 1, 6, 7, 8, 57));
        Assertions.assertEquals(textRecordEntityBriana.getEmail(), "briana_deckow@kihnkessler.us");
        Assertions.assertEquals(textRecordEntityBriana.getSessionId(), "0ef7675c-738d-4528-80d3-3d13aef7c724");

        Assertions.assertEquals(textRecordEntityJarret.getDate(), LocalDateTime.of(2000, 1,6, 7, 46, 21));
        Assertions.assertEquals(textRecordEntityJarret.getEmail(), "jarret_dooley@champlin.info");
        Assertions.assertEquals(textRecordEntityJarret.getSessionId(), "fa80d413-3332-4cc7-af3b-82e52b60cfdb");

        Assertions.assertEquals(textRecordEntityEzra.getDate(), LocalDateTime.of(2000, 1, 6, 12, 49, 17));
        Assertions.assertEquals(textRecordEntityEzra.getEmail(), "ezra.cartwright@bradtke.biz");
        Assertions.assertEquals(textRecordEntityEzra.getSessionId(), "03242e3f-c10a-47b9-93a0-1adace85c27c");
    }

    @Test
    public void Transformer_toResponseDto_Test() {
        TextRecordEntity textRecordEntity = new TextRecordEntity();
        textRecordEntity.setDate(LocalDateTime.of(2000, 1, 6, 7,8,9));
        textRecordEntity.setEmail("chris.thaw@mailinator.com");
        textRecordEntity.setSessionId("03242e3f-c10a-47b9-93a0-1adace85c27c");

        ResponseDto responseDto = Transformer.toResponseDto(textRecordEntity);
        Assertions.assertEquals(responseDto.getEventTime(), "2000-01-06T07:08:09Z");
        Assertions.assertEquals(responseDto.getEmail(), "chris.thaw@mailinator.com");
        Assertions.assertEquals(responseDto.getSessionId(), "03242e3f-c10a-47b9-93a0-1adace85c27c");
    }
}
