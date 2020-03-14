package com.haoxueren.library.regex;

public class RegexUtils {

    private RegexUtils() {
    }

    /**
     * 判断是否为URL
     */
    public static boolean isUrl(String url) {
        // 正则表达式来自StackOverflow
        return url.matches("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    }

}