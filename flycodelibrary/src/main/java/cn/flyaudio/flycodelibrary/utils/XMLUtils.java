package cn.flyaudio.flycodelibrary.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * @author newtrekWang
 * @fileName XMLUtils
 * @createDate 2018/10/31 14:59
 * @email 408030208@qq.com
 * @desc XML工具类
 */
public final class XMLUtils {
    /**
     * 构造器私有
     */
    private XMLUtils(){throw new UnsupportedOperationException("u don't touch me!");}

    /**
     * 换行符
     */
    private static String RT = "\r\n";

    /**
     * map转为简单的xml字符串(一级节点)
     * @param map map对象
     * @return xml字符串
     */
    public static String simpleMapToXml(Map<String, Object> map){
        StringBuilder xmlString = new StringBuilder();
        xmlString.append("<?xml version='1.0' encoding='UTF-8'?>").append(RT);
        xmlString.append("<xml>").append(RT);
        for (Map.Entry<String, Object> entry : map.entrySet()){
            String key = entry.getKey();
            xmlString.append("<" + key + ">" + entry.getValue() + "</" + key + ">").append(RT);
        }
        xmlString.append("</xml>").append(RT);
        return xmlString.toString();
    }

    /**
     * xmlString转为map（一级节点）
     * @param xmlString xmlString
     * @return map
     * @throws Exception
     */
    public static Map<String, Object> getMapFromXML(String xmlString) throws Exception{
        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xmlString.getBytes());
        Document document = builder.parse(is);

        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i = 0;
        while (i < allNodes.getLength()){
            node = allNodes.item(i);
            if (node instanceof Element){
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }


}
