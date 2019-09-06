package cn.flyaudio.flycodelibrary;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cn.flyaudio.flycodelibrary.utils.XMLUtils;

/**
 * @author newtrekWang
 * @fileName XMLUtilsTest
 * @createDate 2018/11/1 16:53
 * @email 408030208@qq.com
 * @desc XML工具单元测试
 */
public class XMLUtilsTest {
    @Test
    public void  simpleMapToXml(){
        Map map = new HashMap();
        map.put("int",1);
        map.put("string","wang");
        map.put("bool",true);
        map.put("long",1000L);
        map.put("char",'a');
        String xmlString = XMLUtils.simpleMapToXml(map);
        System.out.println(xmlString);
    }

    @Test
    public void getMapFromXML(){
        Map map = new HashMap();
        map.put("int",1);
        map.put("string","wang");
        map.put("bool",true);
        map.put("long",1000L);
        map.put("char",'a');
        String xmlString = XMLUtils.simpleMapToXml(map);
        System.out.println(xmlString);
        try {
            map = XMLUtils.getMapFromXML(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解析出的："+map);
    }






}
