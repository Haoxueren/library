package com.haoxueren.library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证号码校验类
 */
public class IDCardHelper {

    /**
     * 根据省份检验用户的身份证号码是否有效
     */
    private static boolean checkByProvince(String idCard) {
        String regex = "(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82)[0-9]{15}([0-9xX])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idCard);
        return matcher.matches();
    }

    /**
     * 对身份证号码的基本格式进行验证
     */
    public static boolean checkIdCardByRegex(String idCard) {
        String regex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idCard);
        return matcher.matches();
    }

    /**
     * 校验18位身份证号码最后一位校验码是否有效
     */
    public static boolean checkIdCardBySum(String idCard) {
        int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; // 身份证前17位第一位对应的权重
        char[] checkCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; // 校验码映射表
        char[] charArray = idCard.toUpperCase().toCharArray(); // 统一转换为大写
        int sum = 0;
        for (int index = 0; index < charArray.length - 1; index++) {
            int charInt = charArray[index] - '0';
            int item = charInt * weight[index]; // 计算每一位对应的权重
            sum = sum + item;
        }
        char checkChar = checkCode[sum % 11]; // 计算校验码
        char lastChar = idCard.charAt(idCard.length() - 1);
        return checkChar == lastChar;
    }
}
