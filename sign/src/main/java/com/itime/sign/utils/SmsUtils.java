package com.itime.sign.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

public class SmsUtils {

    private static final int AppId = 1400122879;

    private static final String AppKey = "525b8522a6be05b990265f1ebebfa538";

    public SmsSingleSenderResult sendOnlySMS(String mobile) {
        try {
            SmsSingleSender sender = new SmsSingleSender(AppId, AppKey);
            SmsSingleSenderResult result = sender.send(0, "86", mobile,
                    "【闲时】您的验证码是" + CodeUtils.verificationCode(6), "", "");
            System.out.println(result);
            return result;
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
