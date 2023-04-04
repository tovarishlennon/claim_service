package com.my.project.claim_service.util;

public class ConvertText {
    public static String convertToDashSeparatedString(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append(c);
            output.append('-');
        }
        output.deleteCharAt(output.length() - 1); // remove the extra '-' at the end
        return output.toString();
    }
}
