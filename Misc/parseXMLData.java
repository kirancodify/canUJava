package data;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class parseXMLData {

	private String[] dataFromXML;
	private String 	uomFromXML;
	private String filteredDataList;
	private String statusDataList;
	private String mnemonicList;
	private String  filteredMnList;
	HashMap dataList = new HashMap();
	HashMap dataList2 = new HashMap();
	HashMap filteredList = new HashMap();

	public HashMap parseOSBData(String xml) {

		boolean status = false;

		  try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();

			Node rootNode = doc.getDocumentElement();

				if (rootNode.getNodeType() == Node.ELEMENT_NODE) {

	    			NodeList firstLevelNodeList = ((Element)rootNode).getElementsByTagName("log");

	    			int noOfChildren = firstLevelNodeList.getLength();

	    			for(int i = 0; i < noOfChildren ; i++){

	    				Node el = (Node)firstLevelNodeList.item(i);

	    				if (el.getNodeType() == Node.ELEMENT_NODE) {

	    					NodeList secondLevelNodeList = ((Element)el).getElementsByTagName("logData");

	    					for(int j = 0; j < secondLevelNodeList.getLength() ; j++){

	    						Node el2 = (Node)secondLevelNodeList.item(j);

	    	    				if (el2.getNodeType() == Node.ELEMENT_NODE) {

	    	    					NodeList mneLevelNodeList = ((Element)el2).getElementsByTagName("mnemonicList");

	    	    					for(int k = 0; k < mneLevelNodeList.getLength() ; k++){

	    	    						Node el3 = (Node)mneLevelNodeList.item(k);

	    	    						mnemonicList = el3.getTextContent();


	    	    					}

	    	    					dataList.put("mnemonicList", mnemonicList);

	    	    					NodeList dataLevelNodeList = ((Element)el2).getElementsByTagName("data");

	    	    					dataFromXML = new String[dataLevelNodeList.getLength()];

	    	    					for(int k = 0; k < dataLevelNodeList.getLength() ; k++){

	    	    						Node el4 = (Node)dataLevelNodeList.item(k);

	    	    						dataFromXML[k] = el4.getTextContent();

	    	    					}
	    	    					 dataList.put("dataFromXML", dataFromXML);

	    	    					NodeList UOMNodeList = ((Element)el2).getElementsByTagName("unitList");

	    	    					for(int k = 0; k < UOMNodeList.getLength() ; k++){

	    	    						Node el4 = (Node)UOMNodeList.item(k);

	    	    						uomFromXML = el4.getTextContent();

	    	    					}
	    	    					 dataList.put("uomFromXML", uomFromXML);

	    	    				}

	    					}


	    				}


	    			}
				}

				if(mnemonicList == null){
					status = false;
				}
				else{
					status = true;
				}

		  } catch (Exception e) {
			e.printStackTrace();
		  }
		  return dataList;
	  }

	public HashMap parseFilteredData(String xml){

		try{

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();

			Node rootNode = doc.getDocumentElement();

			  if (rootNode.getNodeType() == Node.ELEMENT_NODE) {

				  NodeList NodeList = ((Element)rootNode).getElementsByTagName("logData");

	  			int noOfChildren = NodeList.getLength();

	  			for(int i = 0; i < noOfChildren ; i++){

	  				Node el = (Node)NodeList.item(i);

	  				if (el.getNodeType() == Node.ELEMENT_NODE) {

	  					NodeList mnNodeList = ((Element)el).getElementsByTagName("mnemonicList");

	  					 for(int j = 0; j < mnNodeList.getLength() ; j++){

		    						Node el1 = (Node)mnNodeList.item(j);

		    						filteredMnList = el1.getTextContent();

		    			 }

	  					 dataList2.put("filteredMnList", filteredMnList);


	  					 NodeList statusNodeList = ((Element)el).getElementsByTagName("status");

						 Node el3 = (Node)statusNodeList.item(0);

						 statusDataList = el3.getTextContent();

	  					 dataList2.put("statusDataList", statusDataList);

	  					 NodeList dataNodeList = ((Element)el).getElementsByTagName("data");

						 Node el2 = (Node)dataNodeList.item(0);

						 filteredDataList = el2.getTextContent();

	  					 dataList2.put("filteredDataList", filteredDataList);

	  				}

	  			}



			  }
		}
		catch(Exception e){

			e.printStackTrace();

		}

		return dataList2;

	  }
}
