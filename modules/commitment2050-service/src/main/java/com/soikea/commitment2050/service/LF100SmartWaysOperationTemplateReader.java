package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LF100SmartWaysOperationTemplateReader {
    private static Logger _logger = LoggerFactory.getLogger(LF100SmartWaysOperationTemplateReader.class.getName());

    /**
     * Reads Liferay's Operation template article "content" xml and transforms it to json
     * @param xml article content as xml
     * @return Jsob object
     */
    public static JSONObject lfOperationTemplateArticleContentXMLToJSON(String xml) {
        Document document = null;
        try {
            document = SAXReaderUtil.read(xml);
            Element root = document.getRootElement();
            JSONObject json = readElements(root);
            return json;
        } catch (Exception e) {
            _logger.error("100smart ways template article xml to json transform failed! ", e);
        }
        return null;
    }

    private static JSONObject readElements(Element root) {
        JSONObject operation = JSONFactoryUtil.createJSONObject();
        Map<String, JSONArray> jsonArrays = new HashMap<>();

        List<Element> elements = root.elements();
        for (Element e : elements ) {
            String name = e.attributeValue("name");

            if ("meterType".equals(name) || "linkUrl".equals(name)) {
                JSONObject meter = JSONFactoryUtil.createJSONObject();
                createJsonFields(meter, e, name);
                for (Element meterE : e.elements("dynamic-element")) {
                    String name2 = meterE.attributeValue("name");
                    createJsonFields(meter, meterE, name2);
                }
                if (jsonArrays.containsKey(name) ) {
                    jsonArrays.get(name).put(meter);
                } else {
                    JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                    jsonArray.put(meter);
                    jsonArrays.put(name, jsonArray);
                }
            } else {
                createJsonFields(operation, e, name);
            }
        }
        for (String name : jsonArrays.keySet()) {
            operation.put(name + "s", jsonArrays.get(name));
        }
        return operation;
    }

    private static void createJsonFields(JSONObject json, Element e, String name) {
        String value = "";
        JSONObject localizations = JSONFactoryUtil.createJSONObject();
        JSONObject localizedContent = JSONFactoryUtil.createJSONObject();
        for ( Element d : e.elements("dynamic-content")) {
            String lang = d.attributeValue("language-id");
            value = d.getText();
            String key = lang;
            localizedContent.put(lang, value);
        }
        if ( "environmentEffect".equalsIgnoreCase(name) ||
             "decrease".equalsIgnoreCase(name) || "testId".equalsIgnoreCase(name)) {
            json.put(name, value);
        } else {
            json.put(name, localizedContent);
        }
    }
}
