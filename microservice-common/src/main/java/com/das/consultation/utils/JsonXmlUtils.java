package com.das.consultation.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.das.consultation.config.Listener.MarshallerListener;
import com.das.consultation.entities.XmlMessage;
import com.das.consultation.exception.MyException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * created by jun on 2020/8/7
 * describe:自定义xml响应报文工具类
 * version 1.0
 */
public class JsonXmlUtils {
    /**
     * json字符串转为自定义xml响应报文
     *
     * @param json 字符串
     * @return 自定义的xml响应报文
     */
    public static String jsonToXml(String json, XmlMessage xmlMessage) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            if (json != null) {
                JSONObject jObj = JSON.parseObject(json);
                jsonToXmlStr(jObj, buffer, xmlMessage);
            } else {
                buffer.append("<request>" + "<head>" + "<result>").append(xmlMessage.getResult()).append("</result>").append("<desc>").append(xmlMessage.getDesc()).append("</desc>").append("</head>");
                buffer.append(
                        "</request>");
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json数组转为自定义的xml响应报文
     *
     * @param jsonArray   json数组
     * @param listStr     数组字段名
     * @param addStr      与数组同级的字段集合
     * @param addStrValue 与数组同级的字段集合的值
     * @return 自定义的xml响应报文
     */
    public static String jsonArrayToXml(JSONArray jsonArray, XmlMessage xmlMessage, String listStr, String[] addStr, String[] addStrValue) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            jsonArrayToXmlStr(jsonArray, buffer, xmlMessage, listStr, addStr, addStrValue);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json数组转为自定义的xml响应报文
     *
     * @param jsonArray   json数组
     * @param buffer      字符序列
     * @param listStr     数组字段名
     * @param addStr      与数组同级的字段集合
     * @param addStrValue 与数组同级的字段集合的值
     */
    private static void jsonArrayToXmlStr(JSONArray jsonArray, StringBuffer buffer, XmlMessage xmlMessage, String listStr, String[] addStr, String[] addStrValue) {
        String code = xmlMessage.getResult();
        String msg = xmlMessage.getDesc();
        buffer.append("<request>" + "<head>" + "<result>").append(code).append("</result>").append("<desc>").append(msg).append("</desc>").append("</head>").append("<data>");
        //遍历添加数组外的字段和值
        if (addStr != null && addStr.length > 0 && addStrValue.length > 0) {
            for (int i = 0; i < addStr.length; i++) {
                buffer.append("<").append(addStr[i]).append(">").append(addStrValue[i]).append("</").append(addStr[i]).append(">");
            }
        }
        // 遍历JSONArray
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            buffer.append("<").append(listStr).append(">");
            for (Map.Entry entry : jsonObject.entrySet()) {
                buffer.append("<").append(entry.getKey()).append(">").append(entry.getValue());
                buffer.append("</").append(entry.getKey()).append(">");
            }
            buffer.append("</").append(listStr).append(">");
        }

        buffer.append("</data>" +
                "</request>");
    }

    /**
     * json对象转为自定义的xml响应报文
     *
     * @param jObj   json对象
     * @param buffer 字符序列
     */
    private static void jsonToXmlStr(JSONObject jObj, StringBuffer buffer, XmlMessage xmlMessage) {
        String code = xmlMessage.getResult();
        String msg = xmlMessage.getDesc();
        try {
            Set<Map.Entry<String, Object>> se = jObj.entrySet();
            buffer.append("<request>" + "<head>" + "<result>").append(code).append("</result>").append("<desc>").append(msg).append("</desc>").append("</head>").append("<data>");
            for (Map.Entry<String, Object> en : se) {
                buffer.append("<").append(en.getKey()).append(">").append(en.getValue());
                buffer.append("</").append(en.getKey()).append(">");
            }
            buffer.append("</data>" +
                    "</request>");
        } catch (Exception e) {
            e.printStackTrace();
//            buffer.append("<request>" + "<head>" + "<result>").append(code).append("</result>").append("<desc>").append(msg).append("</desc>").append("</head>");
//            buffer.append(
//                    "</request>");
        }

    }


    /**
     * json数组转为自定义的xml响应报文（lists多个多项节点的情况）
     *
     * @param arrayList   json数组集合
     * @param P_listStr   第一层数组字段名
     * @param addStr      与数组同级的字段集合
     * @param addStrValue 与数组同级的字段集合的值
     * @param cList       字节点多项
     * @return 自定义的xml响应报文
     */
    public static String jsonArrayListsToXml(List<JSONArray> arrayList, List<JSONArray> cList, XmlMessage xmlMessage, String[] P_listStr, String[] addStr, String[] addStrValue, String type) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            jsonArrayListsToXmlStr(arrayList, cList, buffer, xmlMessage, P_listStr, addStr, addStrValue, type);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json数组转为自定义的xml响应报文
     *
     * @param arrayList   json数组集合
     * @param buffer      字符序列
     * @param arrayList   数组字段名（lists多个多项节点的情况）
     * @param addStr      与数组同级的字段集合
     * @param cList       字节点多项
     * @param addStrValue 与数组同级的字段集合的值
     */
    private static void jsonArrayListsToXmlStr(List<JSONArray> arrayList, List<JSONArray> cList, StringBuffer buffer, XmlMessage xmlMessage, String[] P_listStr, String[] addStr, String[] addStrValue, String type) {
        String code = xmlMessage.getResult();
        String msg = xmlMessage.getDesc();
        buffer.append("<request>" + "<head>" + "<result>").append(code).append("</result>").append("<desc>").append(msg).append("</desc>").append("</head>").append("<data>");
        //遍历添加数组外的字段和值
        if (addStr != null && addStr.length > 0 && addStrValue.length > 0) {
            for (int i = 0; i < addStr.length; i++) {
                buffer.append("<").append(addStr[i]).append(">").append(addStrValue[i]).append("</").append(addStr[i]).append(">");
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            switch (i) {
                case 0:
                    // 遍历JSONArray
                    for (Object o : arrayList.get(0)) {
                        JSONObject jsonObject = (JSONObject) o;
                        buffer.append("<").append(P_listStr[0]).append(">");
                        for (Map.Entry entry : jsonObject.entrySet()) {
                            buffer.append("<").append(entry.getKey()).append(">").append(entry.getValue());
                            buffer.append("</").append(entry.getKey()).append(">");
                        }
                        if (type.equals("payinfo")) {
                            // 遍历字节点JSONArray
                            for (int j = 0; j < cList.get(0).size(); j++) {
                                /*detail*/
                                buffer.append("<").append(P_listStr[1]).append(">");
                                JSONObject jsonObject1 = (JSONObject) cList.get(0).get(0);
                                for (Map.Entry entry1 : jsonObject1.entrySet()) {
                                    buffer.append("<").append(entry1.getKey()).append(">").append(entry1.getValue());
                                    buffer.append("</").append(entry1.getKey()).append(">");
                                }
                                /*detail*/
                                buffer.append("</").append(P_listStr[1]).append(">");
                            }

                        }
                        buffer.append("</").append(P_listStr[0]).append(">");
                    }


                    break;
                case 1:
                    // 遍历JSONArray
                    for (Object o : arrayList.get(1)) {
                        JSONObject jsonObject = (JSONObject) o;
                        buffer.append("<").append(P_listStr[1]).append(">");
                        for (Map.Entry entry : jsonObject.entrySet()) {
                            buffer.append("<").append(entry.getKey()).append(">").append(entry.getValue());
                            buffer.append("</").append(entry.getKey()).append(">");

                        }
                        /*detl*/
                        buffer.append("<").append(P_listStr[3]).append(">");
                        for (int i1 = 0; i1 < cList.size(); i1++) {
                            if (i1 == 0) {
                                // 遍历字节点JSONArray
                                for (int j = 0; j < cList.get(0).size(); j++) {
                                    if (j == 0) {
                                        JSONObject jsonObject1 = (JSONObject) cList.get(0).get(0);
                                        for (Map.Entry entry1 : jsonObject1.entrySet()) {
                                            buffer.append("<").append(entry1.getKey()).append(">").append(entry1.getValue());
                                            buffer.append("</").append(entry1.getKey()).append(">");
                                        }
                                    }

                                }
                            }
                        }
                        /*detl*/
                        buffer.append("</").append(P_listStr[3]).append(">");
                        buffer.append("</").append(P_listStr[1]).append(">");
                    }


                    break;
                case 2:
                    // 遍历JSONArray
                    for (Object o : arrayList.get(2)) {
                        JSONObject jsonObject = (JSONObject) o;
                        buffer.append("<").append(P_listStr[2]).append(">");
                        for (Map.Entry entry : jsonObject.entrySet()) {
                            buffer.append("<").append(entry.getKey()).append(">").append(entry.getValue());
                            buffer.append("</").append(entry.getKey()).append(">");
                        }
                        /*detl*/
                        buffer.append("<").append(P_listStr[3]).append(">");
                        for (int i1 = 0; i1 < cList.size(); i1++) {
                            if (i1 == 0) {
                                // 遍历字节点JSONArray
                                for (int j = 0; j < cList.get(1).size(); j++) {
                                    if (j == 0) {
                                        JSONObject jsonObject1 = (JSONObject) cList.get(1).get(0);
                                        for (Map.Entry entry1 : jsonObject1.entrySet()) {
                                            buffer.append("<").append(entry1.getKey()).append(">").append(entry1.getValue());
                                            buffer.append("</").append(entry1.getKey()).append(">");
                                        }
                                    }

                                }
                            }
                        }
                        /*detl*/
                        buffer.append("</").append(P_listStr[3]).append(">");

                        buffer.append("</").append(P_listStr[2]).append(">");
                    }


                    break;
            }
        }
        buffer.append("</data>" +
                "</request>");

    }

    /**
     * 反射设置实体不同类型字段的值 <暂时只支持 日期 字符串 boolean Integer值设置 待扩建>
     *
     * @param field
     * @param obj
     * @param value
     * @throws Exception
     */
    public static void convertValue(Field field, Object obj, String value)
            throws Exception {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (field.getGenericType().toString().equals("class java.lang.Integer")) {
            field.set(obj, Integer.parseInt(value));
        } else if (field.getGenericType().toString().equals("boolean")) {
            field.set(obj, Boolean.parseBoolean(value));
        } else if (field.getGenericType().toString().equals(
                "class java.util.Date")) {
            field.set(obj, sim.parse(value));
        } else {
            field.set(obj, value);
        }
    }

    /**
     * DOM4J解析,需要下载对应的jar包
     *
     * @param xmlStr xml字符串
     * @throws Exception
     * @time 2016年1月1日 上午1:52:58
     */
    public static void parse4DOM4j(String xmlStr, Class<?> cls) throws Exception {
        Document doc = DocumentHelper.parseText(xmlStr);
        Element root = doc.getRootElement();
        System.out.println("根节点" + root.getName());
        List<Object> stopVisitNotesInfoList = new ArrayList<>();
        List<Element> childNodes = root.elements();
        for (Element element : childNodes) {
            System.out.println("节点名：" + element.getName() + "---节点值：" + element.getStringValue());
            List<Element> li = element.elements();
            Class<?> cl = Class.forName(cls.getName());
            Object ob = cl.newInstance();
            for (Element element2 : li) {
                String name = element2.getName();
                String value = element2.getText();
                Field field = ob.getClass().getDeclaredField(name);
                field.setAccessible(true);
                convertValue(field, ob, value);
            }
            stopVisitNotesInfoList.add(ob);
        }
//        Iterator iterator = message.elementIterator();
//        while (iterator.hasNext()) {
//            Element stu = (Element) iterator.next();
//            Iterator iterator1 = stu.elementIterator();
//            while (iterator1.hasNext()) {
//                Element stuChild = (Element) iterator1.next();
////                System.out.println("节点名：" + stuChild.getName() + "---节点值：" + stuChild.getStringValue());
//                Iterator iterator2 = stuChild.elementIterator();
//                while (iterator2.hasNext()){
//                    Element stuChild2 = (Element)iterator2.next();
//                    System.out.println("节点名2：" + stuChild2.getName() + "---节点值2：" + stuChild2.getStringValue());
//                }
//            }
//        }
    }

    /**
     * 将xml转换为json对象
     */
    public static JSONObject xmlToJson(String xml) {
        JSONObject jsonObject = new JSONObject();
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new MyException("500", "xml参数格式错误");
        }
        //获取根节点元素对象
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject);
        return jsonObject;
    }

    /**
     * 遍历xml元素
     *
     * @param node 根节点
     * @param json json对象
     */
    private static void iterateNodes(Element node, JSONObject json) {
        //获取当前元素名称
        String nodeName = node.getName();
        //判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName)) {
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else {
                array = new JSONArray();
                array.add(Object);
            }
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                //该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            //有子元素
            JSONObject newJson = new JSONObject();
            //遍历所有子元素
            for (Element e : listElement) {
                //递归
                iterateNodes(e, newJson);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        //该元素同级下第一次遍历
        //获取该元素下所有子元素
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            //该元素无子元素，获取元素的值
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        //有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        for (Element e : listElement) {
            //递归
            iterateNodes(e, object);
        }
        json.put(nodeName, object);
        return;
    }

    /**
     * bean转成xml
     *
     * @param t   具体实体对象
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException
     */
    public static <T> String beanToXml(T t) throws JAXBException {
        //获得 JAXBContext 类的新实例。参数为类的地址
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        //创建一个可以用来将 java 内容树转换为 XML 数据的 Marshaller 对象。
        Marshaller m = context.createMarshaller();
        //创建一个StringWriter流将接收到的对象流写入xml字符串
        StringWriter sw = new StringWriter();
        //调用marshal方法进行转换
        m.marshal(t, sw);
        //将读取到的StringWriter流转成String返回
        return sw.toString();
    }

    /**
     * bean转成xml(泛型使用)
     *
     * @param t   t 具体实体对象
     * @param c   class
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException
     */
    public static <T> String beanToXml(T t, Class c) throws JAXBException {
        //获得 JAXBContext 类的新实例。参数为类的地址
        JAXBContext context = JAXBContext.newInstance(t.getClass(), c);
        //创建一个可以用来将 java 内容树转换为 XML 数据的 Marshaller 对象。
        Marshaller m = context.createMarshaller();
        m.setListener(new MarshallerListener());
        //创建一个StringWriter流将接收到的对象流写入xml字符串
        StringWriter sw = new StringWriter();
        //调用marshal方法进行转换
        m.marshal(t, sw);
        //将读取到的StringWriter流转成String返回
        return sw.toString();
    }


    /**
     * xml 转成 bean
     *
     * @param t   t 具体实体对象
     * @param <T> 泛型
     * @return 实体对象
     * @throws JAXBException
     */
    public static <T> T xmlToBean(String xml, T t) throws JAXBException {
        //获得 JAXBContext 类的新实例。参数为类的地址
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        //创建一个可以用来将 XML 数据转换为 java 内容树的 Unmarshaller 对象。
        Unmarshaller um = context.createUnmarshaller();
        //创建一个StringReader将xml报文转成流
        StringReader sr = new StringReader(xml);
        //调用unmarshal进行转换，并把Object类型强转为调用者的类型
        t = (T) um.unmarshal(sr);
        //将对象返回给调用者
        return t;
    }

    /**
     * xml 转成 bean(泛型使用)
     *
     * @param t   t 具体实体对象
     * @param <T> 泛型
     * @return 实体对象
     * @throws JAXBException
     */
    public static <T> T xmlToBean(String xml, T t, Class c) throws JAXBException {
        //获得 JAXBContext 类的新实例。参数为类的地址
        JAXBContext context = JAXBContext.newInstance(t.getClass(), c);
        //创建一个可以用来将 XML 数据转换为 java 内容树的 Unmarshaller 对象。
        Unmarshaller um = context.createUnmarshaller();
        //创建一个StringReader将xml报文转成流
        StringReader sr = new StringReader(xml);
        //调用unmarshal进行转换，并把Object类型强转为调用者的类型
        t = (T) um.unmarshal(sr);
        //将对象返回给调用者
        return t;
    }


}
