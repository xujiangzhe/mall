package com.cskaoyan.mall.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpUtils {

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, length);
        }
        return byteArrayOutputStream.toString("utf-8");
    }
}
