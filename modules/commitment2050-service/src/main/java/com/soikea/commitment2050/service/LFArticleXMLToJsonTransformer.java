package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LFArticleXMLToJsonTransformer {
    private static Logger _logger = LoggerFactory.getLogger(LFArticleXMLToJsonTransformer.class.getName());

    /**
     * Reads Liferay's Commitment article "content" xml and transforms it to json
     * @param xml article content as xml
     * @return Json object
     */
    public static JSONObject lfCommitmentArticleContentXMLToJSON(String xml) {
        Document document = null;
        try {
            document = SAXReaderUtil.read(xml);
            Element root = document.getRootElement();
            JSONObject json = readCommitmentElements(root);
            return json;
        } catch (Exception e) {
            _logger.error("Commitment article xml to json transform failed! ", e);
        }
        return null;
    }

    private static JSONObject readCommitmentElements(Element root) throws JSONException {
        JSONObject commitment = JSONFactoryUtil.createJSONObject();
        JSONArray operations = JSONFactoryUtil.createJSONArray();
        JSONArray doneOperations = JSONFactoryUtil.createJSONArray();

        List<Element> elements = root.elements();
        for (Element e : elements ) {
            String name = e.attributeValue("name");
            if ("operationTitle".equals(name)) {
                JSONObject operation = JSONFactoryUtil.createJSONObject();
                JSONArray meters = JSONFactoryUtil.createJSONArray();

                createJsonFields(operation, e, name);
                for (Element operationE : e.elements("dynamic-element")) {
                    String name2 = operationE.attributeValue("name");
                    if ("meterType".equals(name2)) {
                        JSONObject meter = JSONFactoryUtil.createJSONObject();
                        createJsonFields(meter, operationE, name2);
                        for (Element meterE : operationE.elements("dynamic-element")) {
                            String name3 = meterE.attributeValue("name");
                            createJsonFields(meter, meterE, name3);
                        }
                        meters.put(meter);


                    } else {
                        createJsonFields(operation, operationE, name2);
                    }
                }
                operation.put("meters", meters);
                operations.put(operation);
            } else if ("doneOperation".equals(name)){
                JSONObject operation = JSONFactoryUtil.createJSONObject();
                createJsonFields(operation, e, name);
                for (Element operationE : e.elements("dynamic-element")) {
                    String name2 = operationE.attributeValue("name");
                    createJsonFields(operation, operationE, name2);
                }
                doneOperations.put(operation);

            } else if ("geolocation".equals(name)){
                for ( Element geo : e.elements("dynamic-content")) {
                    String geoJson = geo.getText();
                    JSONObject geoObj = JSONFactoryUtil.createJSONObject(geoJson);
                    commitment.put("geolocation", geoObj.toJSONString());
                }
            } else {
                createJsonFields(commitment, e, name);
            }
        }
        commitment.put("operations", operations);
        commitment.put("doneOperations", doneOperations);
        return commitment;
    }

    private static void createJsonFields(JSONObject json, Element e, String name) {
        for ( Element d : e.elements("dynamic-content")) {
            String lang = d.attributeValue("language-id");
            String value = d.getText();
            String key = name + "_" + lang;
            json.put(key, value);
        }
    }

    /**
     * Reads Liferay's Report article "content" xml and transforms it to json
     * @param xml article content as xml
     * @return  Json object
     */
    public static JSONObject lfReportArticleContentXMLToJSON(String xml) {
        Document document = null;
        try {
            document = SAXReaderUtil.read(xml);
            Element root = document.getRootElement();
            JSONObject json = readReportElements(root);
            return json;
        } catch (Exception e) {
            _logger.error("Report article xml to json transform failed! ", e);
        }
        return null;
    }

    private static JSONObject readReportElements(Element root) {
        JSONObject report = JSONFactoryUtil.createJSONObject();
        JSONArray meters = JSONFactoryUtil.createJSONArray();

        List<Element> elements = root.elements();
        for (Element e : elements ) {
            String name = e.attributeValue("name");

            if ("meterType".equals(name)) {
                JSONObject meter = JSONFactoryUtil.createJSONObject();
                createJsonFields(meter, e, name);
                for (Element meterE : e.elements("dynamic-element")) {
                    String name2 = meterE.attributeValue("name");
                    createJsonFields(meter, meterE, name2);
                }
                meters.put(meter);
            } else {
                createJsonFields(report, e, name);
            }
        }
        report.put("meters", meters);
        return report;
    }


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
            JSONObject json = readOperationTemplateElements(root);
            return json;
        } catch (Exception e) {
            _logger.error("Report article xml to json transform failed! ", e);
        }
        return null;
    }

    private static JSONObject readOperationTemplateElements(Element root) {
        JSONObject operation = JSONFactoryUtil.createJSONObject();
        JSONArray meters = JSONFactoryUtil.createJSONArray();

        List<Element> elements = root.elements();
        for (Element e : elements ) {
            String name = e.attributeValue("name");

            if ("meterType".equals(name)) {
                JSONObject meter = JSONFactoryUtil.createJSONObject();
                createJsonFields(meter, e, name);
                for (Element meterE : e.elements("dynamic-element")) {
                    String name2 = meterE.attributeValue("name");
                    createJsonFields(meter, meterE, name2);
                }
                meters.put(meter);
            } else {
                createJsonFields(operation, e, name);
            }
        }
        operation.put("meters", meters);
        return operation;
    }

    /**
     * Reads Liferay's article  "content" xml and transforms it to json.
     * This can be used for all article structures which does not include repeatable fields
     * @param xml article content as xml
     * @return  json object
     */
    public static JSONObject lfSimpleFlatArticleContentXMLToJSON(String xml) {
        Document document = null;
        try {
            document = SAXReaderUtil.read(xml);
            Element root = document.getRootElement();
            JSONObject json = readSimpleTemplateElements(root);
            return json;
        } catch (Exception e) {
            _logger.error("Report article xml to json transform failed! ", e);
        }
        return null;
    }

    private static JSONObject readSimpleTemplateElements(Element root) {
        JSONObject meter = JSONFactoryUtil.createJSONObject();
        List<Element> elements = root.elements();
        for (Element e : elements ) {
            String name = e.attributeValue("name");
            createJsonFields(meter, e, name);
        }
        return meter;
    }
}
