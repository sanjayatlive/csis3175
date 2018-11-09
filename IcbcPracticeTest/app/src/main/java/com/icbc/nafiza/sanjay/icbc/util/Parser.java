package com.icbc.nafiza.sanjay.icbc.util;

import android.util.Log;

import com.icbc.nafiza.sanjay.icbc.bean.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public  class Parser {

   public  static List<Item> dataList = new ArrayList<>();

   public static List<Item> parseIt(String response) throws Exception
   {

      String tag="";
      // List<Item> list = new ArrayList<>();
      Item item = new Item();

      int id = 0;

      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      factory.setNamespaceAware(true);
      XmlPullParser xpp = factory.newPullParser();

      xpp.setInput( new StringReader( response ) );
      int eventType = xpp.getEventType();
      while (eventType != XmlPullParser.END_DOCUMENT) {
         if(eventType == XmlPullParser.START_DOCUMENT) {
            //    System.out.println("Start document");
         } else if(eventType == XmlPullParser.START_TAG) {

            //  System.out.println("xpp.getName().trim()" + xpp.getName().trim());

            if(xpp.getName().trim().equals("question")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );


               // System.out.println("insideqs");
            }


            else if(xpp.getName().trim().equalsIgnoreCase("text")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );
               tag = "text";
            }
            else if(xpp.getName().trim().equalsIgnoreCase("answer")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );
               tag = "answer";
            }
            else if(xpp.getName().trim().equalsIgnoreCase("distractor_1")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );
               tag = "distractor_1";
            }
            else if(xpp.getName().trim().equalsIgnoreCase("distractor_2")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );
               tag = "distractor_2";
            }
            else if(xpp.getName().trim().equalsIgnoreCase("distractor_3")){
               //System.out.println("<<<<<<<<<>>>>>>>>" +xpp.getName() );
               tag = "distractor_3";
            }else
            {
               tag = "";
            }

            //System.out.println("Start tag "+xpp.getName());
         } else if(eventType == XmlPullParser.END_TAG) {
            //System.out.println("End tag "+xpp.getName());
         } else if(eventType == XmlPullParser.TEXT) {
            //System.out.println("Text "+xpp.getText());

            if(tag.equals("text"))
            {

               if(xpp.getText().toString().trim().length()!=0)
               {
                  //  System.out.println(">>>>>>>insideqs" + xpp.getText().toString());
                  item.setQuestion(xpp.getText().trim());
               }


            }
            else if(tag.equals("answer"))
            {


               if(xpp.getText().toString().trim().length()!=0)
               {
                  //  System.out.println(">>>>>>>answer" + xpp.getText().toString());
                  item.setAnswer(xpp.getText().trim());
               }


            }
            else if(tag.equals("distractor_1"))
            {
               if(xpp.getText().toString().trim().length()!=0) {
                  item.setDistractor1(xpp.getText().trim());

                  // System.out.println(">>>>>>>distractor_1" + xpp.getText().toString());
               }
            }
            else if(tag.equals("distractor_2"))
            {
               if(xpp.getText().toString().trim().length()!=0) {
                  item.setDistractor2(xpp.getText().trim());
               }

            }
            else if(tag.equals("distractor_3"))
            {
               if(xpp.getText().toString().trim().length()!=0) {
                  item.setDistractor3(xpp.getText().trim());

                  dataList.add(item);
                  id++;
                  item = new Item();
                  item.setId(id);
               }

            }

         }
         eventType = xpp.next();
      }
      // System.out.println("End document");

      //  return list;

return dataList;


   }



}
