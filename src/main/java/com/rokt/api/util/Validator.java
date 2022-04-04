package com.rokt.api.util;

import java.time.LocalDateTime;

public class Validator {

    private static final String ISO_REGEX_STRING = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(.[0-9]+)?(Z)?$";

    public static Boolean isISOFormat(String value) {
        return value != null && value.matches(ISO_REGEX_STRING);
    }

    public static Boolean isValidDateRange(LocalDateTime start, LocalDateTime end) {
        return !start.isAfter(end);
    }
}
