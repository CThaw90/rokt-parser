package com.rokt.api.util;

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
}
