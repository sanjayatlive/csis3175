package com.icbc.nafiza.sanjay.icbc.util;

import com.icbc.nafiza.sanjay.icbc.bean.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Item> dataList = new ArrayList<>();

    public static List<Item> parseIt(String response) throws Exception {

        String tag = "";
        Item item = new Item();

        int id = 0;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader(response));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
            } else if (eventType == XmlPullParser.START_TAG) {
                if (xpp.getName().trim().equals("question")) {
                } else if (xpp.getName().trim().equalsIgnoreCase("text")) {
                    tag = "text";
                } else if (xpp.getName().trim().equalsIgnoreCase("answer")) {
                    tag = "answer";
                } else if (xpp.getName().trim().equalsIgnoreCase("distractor_1")) {
                    tag = "distractor_1";
                } else if (xpp.getName().trim().equalsIgnoreCase("distractor_2")) {
                    tag = "distractor_2";
                } else if (xpp.getName().trim().equalsIgnoreCase("distractor_3")) {
                    tag = "distractor_3";
                } else if (xpp.getName().trim().equalsIgnoreCase("image")) {
                    dataList.remove(dataList.size() - 1);

                } else {
                    tag = xpp.getName().trim();
                }
            } else if (eventType == XmlPullParser.END_TAG) {
            } else if (eventType == XmlPullParser.TEXT) {
                if (tag.equals("text")) {
                    if (xpp.getText().toString().trim().length() != 0) {
                        item.setQuestion(xpp.getText().trim());
                    }
                } else if (tag.equals("answer")) {
                    if (xpp.getText().toString().trim().length() != 0) {
                        item.setAnswer(xpp.getText().trim());
                    }
                } else if (tag.equals("distractor_1")) {
                    if (xpp.getText().toString().trim().length() != 0) {
                        item.setDistractor1(xpp.getText().trim());
                    }
                } else if (tag.equals("distractor_2")) {
                    if (xpp.getText().toString().trim().length() != 0) {
                        item.setDistractor2(xpp.getText().trim());
                    }
                } else if (tag.equals("distractor_3")) {
                    if (xpp.getText().toString().trim().length() != 0) {
                        item.setDistractor3(xpp.getText().trim());
                        dataList.add(item);
                        item = new Item();
                    }

                }

            }
            eventType = xpp.next();
        }
        setIds();
        return dataList;
    }

    public static void setIds() {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).setId(i);
        }
    }

}
