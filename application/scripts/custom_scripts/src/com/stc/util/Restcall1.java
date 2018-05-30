package com.stc.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.PropertyConfigurator;

public class Restcall1 {
		public static void createnewIssue(String xmlFileName) throws Exception {
			System.out.println("Filename : " + xmlFileName);
		URL url = new URL("https://www51.v1host.com/ITCInfotech62/rest-1.v1/Data/Defect");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       // logger.info("url: " + url);
        // String authString = config.getKey("developerUsername") + ":" +
        // config.getKey("developerPassword");
        String authString = "admin:zicosadmin@123";
        String authStringEnc = "Basic " + new String((new Base64()).encode(authString.getBytes()));
        conn.setRequestProperty("Authorization", authStringEnc.replaceAll("\n", ""));
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/xml");
        OutputStream os = conn.getOutputStream();
        //FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\jiradata.xml");
        FileInputStream inputStream = new FileInputStream(xmlFileName);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StreamSource source = new StreamSource(inputStream);
        StreamResult result = new StreamResult(os);
        transformer.transform(source, result);    
        if (200 == conn.getResponseCode()) {
              //System.out.println("success");
        } else {
        	//System.out.println("failure");
        }
        }
}