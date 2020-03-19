package com.haoxueren.library.utils;

/**
 * Emoji表情工具类(有遗漏，无法识别所有emoji表情)
 *
 * create by haomingliang on 2020/3/19
 */
public class Emoji {

    private Emoji() {
    }

    /**
     * 检测文本中是否有Emoji表情
     */
    public static boolean hasEmoji(String source) {
        int length = source.length();
        for (int i = 0; i < length; i++) {
            char codePoint = source.charAt(i);
            if (Emoji.isEmoji(codePoint)) {
                return true;  // 如果不能匹配,则该字符是Emoji表情
            }
        }
        return false;
    }

    /**
     * 判断单个字符是否为Emoji表情
     * true=>emoji; false=>!emoji
     */
    private static boolean isEmoji(char codePoint) {
        return !((codePoint == 0x0)
                || (codePoint == 0x9)
                || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));

    }
}
