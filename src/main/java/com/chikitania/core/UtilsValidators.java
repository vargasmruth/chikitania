package com.chikitania.core;

import java.util.Date;
import java.util.Optional;

/**
 * Created by veronica on 01/11/2017.
 */
public class UtilsValidators {

    public static boolean isLongValid(long value) {
        return value != 0;
    }

    public static boolean isStringValid(String value) {
        return Optional.ofNullable(value).isPresent() && !value.trim().isEmpty();
    }

    public static boolean isDoubleValid(double value) {
        return value != 0;
    }

    public static boolean isObjectValid(Object value) {
        return Optional.ofNullable(value).isPresent();
    }

    public static boolean isFloatValid(float value) {
        return value != 0;
    }

    public static boolean isDateValid(Date value) {
        return Optional.ofNullable(value).isPresent();
    }
}
