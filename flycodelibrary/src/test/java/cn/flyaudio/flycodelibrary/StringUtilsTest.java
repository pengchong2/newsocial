package cn.flyaudio.flycodelibrary;

import org.junit.Test;

import cn.flyaudio.flycodelibrary.utils.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author newtrekWang
 * @fileName StringUtilsTest
 * @createDate 2018/10/31 15:15
 * @email 408030208@qq.com
 * @desc 字符串工具单元测试
 */
public class StringUtilsTest {
    @Test
    public void isEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertTrue(StringUtils.isEmpty(null));
        assertFalse(StringUtils.isEmpty(" "));
    }

    @Test
    public void isTrimEmpty() {
        assertTrue(StringUtils.isTrimEmpty(""));
        assertTrue(StringUtils.isTrimEmpty(null));
        assertTrue(StringUtils.isTrimEmpty(" "));
    }

    @Test
    public void isSpace() {
        assertTrue(StringUtils.isSpace(""));
        assertTrue(StringUtils.isSpace(null));
        assertTrue(StringUtils.isSpace(" "));
        assertTrue(StringUtils.isSpace("　 \n\t\r"));
    }

    @Test
    public void equals() {
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("blankj", "blankj"));
        assertFalse(StringUtils.equals("blankj", "Blankj"));
    }

    @Test
    public void equalsIgnoreCase() {
        assertTrue(StringUtils.equalsIgnoreCase(null, null));
        assertFalse(StringUtils.equalsIgnoreCase(null, "blankj"));
        assertTrue(StringUtils.equalsIgnoreCase("blankj", "Blankj"));
        assertTrue(StringUtils.equalsIgnoreCase("blankj", "blankj"));
        assertFalse(StringUtils.equalsIgnoreCase("blankj", "blank"));
    }

    @Test
    public void null2Length0() {
        assertEquals("", StringUtils.null2Length0(null));
    }

    @Test
    public void length() {
        assertEquals(0, StringUtils.length(null));
        assertEquals(0, StringUtils.length(""));
        assertEquals(6, StringUtils.length("blankj"));
    }

    @Test
    public void upperFirstLetter() {
        assertEquals("Blankj", StringUtils.upperFirstLetter("blankj"));
        assertEquals("Blankj", StringUtils.upperFirstLetter("Blankj"));
        assertEquals("1Blankj", StringUtils.upperFirstLetter("1Blankj"));
    }

    @Test
    public void lowerFirstLetter() {
        assertEquals("blankj", StringUtils.lowerFirstLetter("blankj"));
        assertEquals("blankj", StringUtils.lowerFirstLetter("Blankj"));
        assertEquals("1blankj", StringUtils.lowerFirstLetter("1blankj"));
    }

    @Test
    public void reverse() {
        assertEquals("jknalb", StringUtils.reverse("blankj"));
        assertEquals("knalb", StringUtils.reverse("blank"));
        assertEquals("文中试测", StringUtils.reverse("测试中文"));
        assertEquals("", StringUtils.reverse(null));
    }

    @Test
    public void joint() {
        assertEquals("abcde", StringUtils.joint("a","b","c","d","e"));
        assertEquals("abc", StringUtils.joint("abc"));
        assertEquals("abcde", StringUtils.joint("abc","de"));
    }

    @Test
    public void trim() {
        assertEquals("a", StringUtils.trim(" a"));
        assertEquals("a", StringUtils.trim("a "));
        assertEquals("a", StringUtils.trim(" a "));
    }

    @Test
    public void deleteWhitespace() {
        assertEquals("abc", StringUtils.deleteWhitespace(" a bc"));
        assertEquals("abc", StringUtils.deleteWhitespace("a b   c"));
        assertEquals("abc", StringUtils.deleteWhitespace(" a bc   "));
    }

    @Test
    public void split2Array(){
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a,b,c",","));
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a b c"," "));
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a-b-c","-"));
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a\\b\\c","\\\\"));
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a.b.c","\\."));
        assertEquals(new String[]{"a","b","c"},StringUtils.split2Array("a、b、c","\\、"));
    }

    @Test
    public void getIntArray(){
        int[] ex = new int[]{1,2,3,4};
        int[] result = StringUtils.getIntArray("1/2/3/4","/");
        assertEquals(ex.length,result.length);
        for (int i=0;i<ex.length;i++){
            assertEquals(ex[i],result[i]);
        }
    }

    @Test
    public void getChildStringCount(){
        assertEquals(2,StringUtils.getChildStringCount("abcfdasdfasfasabcfdasfa","abc"));
        assertEquals(2,StringUtils.getChildStringCount("abcfdasdfasfasabcfdasfa","bc"));
    }

    @Test
    public void subString(){
        assertEquals("cde",StringUtils.subString("abcde",2));
        assertEquals("cde",StringUtils.subString("abcdefg",2,4));
    }




}
