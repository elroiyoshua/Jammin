package com.example.jamminbaru;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}


//                }if(!emailTxt.isEmpty()) {
//
//                    if (EmailValidator.validateEmail(emailTxt)) {
//                        edit_email.setError("Email is valid");
//                    } else {
//                        edit_email.setError("Email is invalid");
//                    }
//
////                    try {
////                        if (EmailValidator.validateEmail(emailTxt)) {
////                            edit_email.setError("Email is valid");
////                        } else {
////                            edit_email.setError("Email is invalid");
////                        }
////                    } catch (Exception e) {
////                        System.out.println("An error occurred: " + e.getMessage());
////                    }