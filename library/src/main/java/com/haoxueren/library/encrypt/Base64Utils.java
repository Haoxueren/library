package com.haoxueren.library.encrypt;


import android.util.Base64;

import java.nio.charset.StandardCharsets;


/**
 * create by haomingliang on 2020/3/9
 */
public class Base64Utils {

    private Base64Utils() {
    }

    /**
     * 对文本进行Base64编码
     */
    public static String encode(String content) throws Exception {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        byte[] encodeBytes = Base64.encode(contentBytes, Base64.DEFAULT);
        return new String(encodeBytes, StandardCharsets.UTF_8);
    }

    /**
     * 将Base64解码为文本
     */
    public static String decode(String content) {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        byte[] decodeBytes = Base64.decode(contentBytes, Base64.DEFAULT);
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }
}
