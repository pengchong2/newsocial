package cn.flyaudio.flycodelibrary.xml;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * xml解析（Dom）
 * @author meihui
 */
public class XmlParsingUtils {
    private static final String TAG = "DomParsingXmlUtils";

    private XmlParsingUtils(){}

    /**
     * dom解析之读取xml文件,返回beanList
     *
     * @param xmlStream 文档输入流
     * @return xml解析后的beanList
     */
    public static List<XmlBean> readXmlToBean(InputStream xmlStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlStream);
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getChildNodes();
            List<XmlBean> beans = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() != Node.TEXT_NODE) {

                    NamedNodeMap attributes = node.getAttributes();

                    XmlBean bean = new XmlBean();
                    bean.setItem(node.getNodeName());
                    for (int j = 0; j < attributes.getLength(); j++) {

                        Node item = attributes.item(j);
                        if (item.getNodeName().contains("background")) {
                            bean.setBackground(item.getNodeValue());
                        } else if (item.getNodeName().contains("classname")) {
                            bean.setClassname(item.getNodeValue());
                        } else if (item.getNodeName().contains("m_Text")) {
                            bean.setM_Text(item.getNodeValue());
                        } else if (item.getNodeName().contains("packagename")) {
                            bean.setPackagename(item.getNodeValue());
                        } else if (item.getNodeName().contains("tag")) {
                            bean.setTag(item.getNodeValue());
                        }

                    }
                    beans.add(bean);

                }

            }
            return beans;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * dom解析之读取xml文件，返回map，key是属性名，value是值
     *
     * @param xmlStream
     * @return
     */
    public static List<Map<String, String>> readXmlToMap(InputStream xmlStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlStream);
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getChildNodes();
            List<Map<String, String>> maps = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() != Node.TEXT_NODE) {
                    Map<String, String> map = new HashMap<>();
                    NamedNodeMap attributes = node.getAttributes();
                    map.put("item", node.getNodeName());
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node item = attributes.item(j);

                        String key = item.getNodeName();
                        map.put(key.substring(key.lastIndexOf(":")+1), item.getNodeValue());
                    }
                    maps.add(map);
                }

            }

            return maps;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
