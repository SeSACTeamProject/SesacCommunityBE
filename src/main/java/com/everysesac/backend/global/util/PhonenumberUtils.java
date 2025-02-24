package com.everysesac.backend.global.util;

public class PhonenumberUtils {
    public static String extractLastFourDigits(String phonenumber) {
        phonenumber = phonenumber.replaceAll("[^0-9]", "");

        return phonenumber.substring(phonenumber.length() - 4);
    }
}
