package cn.flyaudio.flycodelibrary;

import org.junit.Test;

import java.util.Arrays;


import cn.flyaudio.flycodelibrary.utils.RegexUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @className RegexUtilsTest
 * @createDate 2018/11/15 16:30
 * @author newtrekWang
 * @email 408030208@qq.com
 * @desc 正则表达式工具单元测试
 *
 */
public class RegexUtilsTest {

    @Test
    public void isMobileSimple() {
        assertTrue(RegexUtils.isMobileSimple("11111111111"));
    }

    @Test
    public void isMobileExact() {
        assertFalse(RegexUtils.isMobileExact("11111111111"));
        assertTrue(RegexUtils.isMobileExact("13888880000"));
    }

    @Test
    public void isTel() {
        assertTrue(RegexUtils.isTel("033-88888888"));
        assertTrue(RegexUtils.isTel("033-7777777"));
        assertTrue(RegexUtils.isTel("0444-88888888"));
        assertTrue(RegexUtils.isTel("0444-7777777"));
        assertTrue(RegexUtils.isTel("033 88888888"));
        assertTrue(RegexUtils.isTel("033 7777777"));
        assertTrue(RegexUtils.isTel("0444 88888888"));
        assertTrue(RegexUtils.isTel("0444 7777777"));
        assertTrue(RegexUtils.isTel("03388888888"));
        assertTrue(RegexUtils.isTel("0337777777"));
        assertTrue(RegexUtils.isTel("044488888888"));
        assertTrue(RegexUtils.isTel("04447777777"));

        assertFalse(RegexUtils.isTel("133-88888888"));
        assertFalse(RegexUtils.isTel("033-666666"));
        assertFalse(RegexUtils.isTel("0444-999999999"));
    }

    @Test
    public void isIDCard18() {
        assertTrue(RegexUtils.isIDCard18("33698418400112523x"));
        assertTrue(RegexUtils.isIDCard18("336984184001125233"));
        assertFalse(RegexUtils.isIDCard18("336984184021125233"));
    }

    @Test
    public void isIDCard18Exact() {
        assertFalse(RegexUtils.isIDCard18Exact("33698418400112523x"));
        assertTrue(RegexUtils.isIDCard18Exact("336984184001125233"));
        assertFalse(RegexUtils.isIDCard18Exact("336984184021125233"));
    }

    @Test
    public void isEmail() {
        assertTrue(RegexUtils.isEmail("408030208@qq.com"));
        assertFalse(RegexUtils.isEmail("blankj@qq"));
    }

    @Test
    public void isURL() {
        assertTrue(RegexUtils.isURL("http://blankj.com"));
        assertFalse(RegexUtils.isURL("https:blank"));
    }

    @Test
    public void isZh() {
        assertTrue(RegexUtils.isZh("我"));
        assertFalse(RegexUtils.isZh("wo"));
    }

    @Test
    public void isDate() {
        assertTrue(RegexUtils.isDate("2016-08-16"));
        assertTrue(RegexUtils.isDate("2016-02-29"));
        assertFalse(RegexUtils.isDate("2015-02-29"));
        assertFalse(RegexUtils.isDate("2016-8-16"));
    }

    @Test
    public void isIP() {
        assertTrue(RegexUtils.isIP("255.255.255.0"));
        assertFalse(RegexUtils.isIP("256.255.255.0"));
    }

    @Test
    public void isMatch() {
        assertTrue(RegexUtils.isMatch("\\d?", "1"));
        assertFalse(RegexUtils.isMatch("\\d?", "a"));
    }

    @Test
    public void getMatches() {
        // 贪婪
        System.out.println(RegexUtils.getMatches("h.*g", "happyCodinghappyCoding"));
        // 懒惰
        System.out.println(RegexUtils.getMatches("h.*?g", "happyCodinghappyCoding"));
    }

    @Test
    public void getSplits() {
        System.out.println(Arrays.asList(RegexUtils.getSplits("1,2,3", ",")));
    }

    @Test
    public void getReplaceFirst() {
        System.out.println(RegexUtils.getReplaceFirst("1 2 3", " ", ", "));
    }

    @Test
    public void getReplaceAll() {
        System.out.println(RegexUtils.getReplaceAll("1 2 3", " ", ", "));
    }
}