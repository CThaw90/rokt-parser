package com.rokt.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

public class ValidatorTest {

    @Test
    public void Validator_isISOFormat_Test() {
        Validator validator = new Validator();
        Assertions.assertTrue(validator.isISOFormat("2000-01-01T03:05:58Z"));
        Assertions.assertFalse(validator.isISOFormat("01-01-2000"));
        Assertions.assertFalse(validator.isISOFormat("2000-02-02 03:03:12"));
        Assertions.assertFalse(validator.isISOFormat("12:30:59Z"));
        Assertions.assertTrue(validator.isISOFormat("2021-09-30T12:30:59Z"));
        Assertions.assertTrue(validator.isISOFormat("2021-12-31T08:15:30Z"));
        Assertions.assertFalse(validator.isISOFormat("\uFEFF2000-01-01T13:38:29Z"));
        Assertions.assertFalse(validator.isISOFormat(null));
        Assertions.assertFalse(validator.isISOFormat("2000-01-28T25:49:35Z"));
    }

    @Test
    public void Validator_isValidDateRange_Test() {
        LocalDateTime start = LocalDateTime.of(2000, 12, 30, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2001, 12, 30, 12, 59, 59);
        Validator validator = new Validator();
        Assertions.assertTrue(validator.isValidDateRange(start, end));

        start = LocalDateTime.of(2005, 12, 30, 10, 30, 30);
        end = LocalDateTime.of(2005, 12, 30, 10, 20, 30);
        Assertions.assertFalse(validator.isValidDateRange(start, end));

        start = LocalDateTime.of(2010, 12, 30, 10, 10, 10);
        end = LocalDateTime.of(2010, 12, 31, 10, 10, 10);
        Assertions.assertTrue(validator.isValidDateRange(start, end));
    }
}
