package com.haoxueren.library.base16;

import java.nio.charset.StandardCharsets;

public class Base16 {

    private static final String META = "base16:";


    private Base16() {

    }

    /**
     * 对文本进行 base16 编码
     */
    public static String encode(String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8); // 转换为字节数组
        StringBuilder builder = new StringBuilder();
        builder.append(Base16.META);
        for (byte b : bytes) {
            byte b1 = (byte) (b >> 4 & 0b00001111); // 取高4位
            byte b2 = (byte) (b & 0b00001111); // 取低4位
            String s1 = Integer.toHexString(b1);
            String s2 = Integer.toHexString(b2);
            builder.append(s1).append(s2);
        }
        return builder.toString();
    }

    /**
     * 将 base16 解码为明文
     */
    public static String decode(String base16) {
        base16 = base16.replaceFirst(Base16.META, "");
        char[] chars = base16.toCharArray();
        byte[] bytes = new byte[chars.length / 2];
        for (int i = 0; i < chars.length; i += 2) {
            String s1 = String.valueOf(chars[i]);
            String s2 = String.valueOf(chars[i + 1]);
            byte b1 = Byte.parseByte(s1, 16);
            byte b2 = Byte.parseByte(s2, 16);
            b1 = (byte) (b1 << 4);
            byte b = (byte) (b1 + b2);
            bytes[i / 2] = b;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static boolean isBase16(String text) {
        if (text == null) {
            return false;
        }
        if ((text.length() & 1) == 1) {
            return false;
        }
        return text.matches("base16:[0-9a-f]{2,}");
    }
}
