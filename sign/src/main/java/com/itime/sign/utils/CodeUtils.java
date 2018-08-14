package com.itime.sign.utils;

import java.util.Random;

public class CodeUtils {

    public static final String AllChar = "0123456789";

    public static String verificationCode(int length) {

        String code = "";
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            stringBuffer.append(AllChar.charAt(random.nextInt(AllChar.length())));
        }
        code = stringBuffer.toString();

        return code;
    }


}
