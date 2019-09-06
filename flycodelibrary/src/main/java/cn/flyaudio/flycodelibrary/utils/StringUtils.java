package cn.flyaudio.flycodelibrary.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author newtrekWang
 * @fileName StringUtils
 * @createDate 2018/10/26 14:44
 * @email 408030208@qq.com
 * @desc 字符串工具类
 */
public final class StringUtils {
    /**
     * 构造器私有
     */
    private StringUtils(){
        throw new UnsupportedOperationException("u don't touch me!");
    }
    /**
     * 判断字符串是否为null或者长度为0
     * @param s 要判断的字符串
     * @return 字符串是否为null或者长度为0
     */
    public static boolean isEmpty(@Nullable final CharSequence s){
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串去掉头尾空白符后是否为空
     * @param s 要判断的字符串
     * @return 字符串是否为null或者长度为0
     */
    public static boolean isTrimEmpty(@Nullable final String s){
        return (s==null || s.trim().length() == 0);
    }

    /**
     * 判断字符串是否为null或者是空白符
     * @param str 要判断的字符串
     * @return  字符串是否为null或者是空白符
     */
    public static boolean isSpace(@Nullable final String str){
        if (str == null) {return true;}
        for (int i = 0, len = str.length();i < len; ++i){
            if (!Character.isWhitespace(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个字符串是否值相同
     *
     * @param s1 第一个字符串
     * @param s2 第二个字符串
     * @return 两个字符串是否值相同
     */
    public static boolean equals(@Nullable final CharSequence s1,@Nullable final CharSequence s2) {
        if (s1 == s2) {return true;}
        int length;
        if (s1 != null && s2 != null && (length = s1.length()) == s2.length()) {
            if (s1 instanceof String && s2 instanceof String) {
                return s1.equals(s2);
            } else {
                for (int i = 0; i < length; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 如果字符串为null,返回一个空字串
     *
     * @param s 要处理的字符串
     * @return 如果字符串为null,返回一个空字串，否则为原子串
     */
    public static String null2Length0(final String s) {
        return s == null ? "" : s;
    }
    /**
     * 返回字符串的长度
     *
     * @param s 要处理的字符串
     * @return 字符串的长度
     */
    public static int length(@Nullable final CharSequence s) {
        return s == null ? 0 : s.length();
    }
    /**
     * 忽略大小写判断两个字符串是否值相同
     *
     * @param s1 第一个字符串
     * @param s2 第二个字符串
     * @return 忽略大小写两个字符串是否值相同
     */
    public static boolean equalsIgnoreCase(@Nullable final String s1,@Nullable final String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    /**
     * 大写第一个字母
     *
     * @param s 要处理的字符串
     * @return 大写第一个字母后的字符串
     */
    public static String upperFirstLetter(final String s) {
        if (s == null || s.length() == 0) {return "";}
        if (!Character.isLowerCase(s.charAt(0))) {return s;}
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 小写第一个字母
     *
     * @param s 要处理的字符串
     * @return 小写第一个字母后的字符串
     */
    public static String lowerFirstLetter(final String s) {
        if (s == null || s.length() == 0) {return "";}
        if (!Character.isUpperCase(s.charAt(0))) {return s;}
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }
    /**
     * 反转字符串
     *
     * @param s 要处理的字符串
     * @return 反转后的字符串
     */
    public static String reverse(final String s) {
        if (s == null) {return "";}
        int len = s.length();
        if (len <= 1) {return s;}
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }
    /**
     * 从字符串指定位置开始截取到最后，比如输入("abcde",1),结果为"bcde"
     * @param str 要处理的字符串
     * @param startIndex 要截取的开始位置
     * @return 截取的字符串
     */
    public static String subString(@NonNull final String  str, int startIndex){
        return str.substring(startIndex);
    }
    /**
     * 从字符串指定位置开始截取到结束位置（包含结束位置），比如输入("abcde",1,3),结果为"bcd"
     * @param str 要处理的字符串
     * @param startIndex 要截取的开始位置
     * @param endIndex 要截取的结束位置
     * @return 截取的字符串
     */
    public static String subString(@NonNull final String  str, int startIndex, int endIndex){
        return str.substring(startIndex,endIndex+1);
    }

    /**
     * 连接字符串
     * @param strings 要连接的字符串，可变长参数
     * @return 连接后的字符串
     */
    public static String joint(String... strings){
        StringBuilder builder = new StringBuilder(strings.length);
        for (String item : strings){
            builder.append(item);
        }
        return builder.toString();
    }

    /**
     * 去掉开头和结尾的空格
     * @param str 要处理的字符串
     * @return 去掉开头和结尾的空格后的字符串
     */
    public static String trim(@NonNull String str){
        if (str == null){
            throw new NullPointerException("str is null");
        }
        return str.trim();
    }

    /**
     * 去掉所有空格
     * @param str
     * @return
     */
    public static String deleteWhitespace(@NonNull String str){
        if (str == null){
            throw new NullPointerException("str is null");
        }
        return str.replace(" ","");
    }

    /**
     * 拆分字符串
     * @param str 要处理的字符串
     * @param regex 正则表达式分隔符,比如空格用
     *              注意： . 、 | 和 * 等转义字符，必须得加 \\。
     *              注意：多个分隔符，可以用 | 作为连字符。
     *
     * @return 拆分后的字符串数组
     */
    public static String[] split2Array(@NonNull String str,@NonNull String regex){
        if (str == null || regex == null){
            throw new NullPointerException();
        }
        return str.split(regex);
    }

    /**
     * 获取字串个数
     * @param str 要处理的
     * @param childStr 字串，可以用正则表达式分隔符
     * @return 字串个数
     */
    public static int getChildStringCount(@NonNull String str,@NonNull String childStr){
        if (str == null || childStr == null){
            throw new NullPointerException();
        }
        String[] split = str.split(childStr);
        return split.length-1;
    }

    /**
     * 提取出int数组
     * @param str 要处理的字符串，必须为数字加分割符连接起来的格式，比如 "1/2/3"
     * @param regex 分割符，注意： . 、 | 和 * 等转义字符，必须得加 \\。
     *                     注意：多个分隔符，可以用 | 作为连字符。
     * @return int数组
     */
    public static int[] getIntArray(@NonNull String str,@NonNull String regex){
        if (str == null || regex == null){
            throw new NullPointerException();
        }
        String[] split = str.split(regex);
        if (split==null){return null;}
        int[] result = new int[split.length];
        for (int i=0;i<split.length;i++){
            int item = Integer.parseInt(split[i]);
            result[i]=item;
        }
        return result;
    }
}
