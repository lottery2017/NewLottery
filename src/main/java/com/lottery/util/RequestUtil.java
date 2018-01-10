package com.lottery.util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gaojunc on 2018/1/6 10:19.
 * Created Reason:
 */
public class RequestUtil {

    public static String _getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream stream = request.getInputStream();
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        stream.read(buffer, 0, len);
        return new String(buffer, "utf-8");
    }

}
