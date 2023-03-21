package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {

        if(email == null || email.isBlank()) return false;

        Pattern pattern = Pattern.compile("((\\w+_)\\w+)@(epam.com)");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}





