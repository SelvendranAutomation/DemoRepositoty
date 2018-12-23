package GRMAPITesting.com.org.classes;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UpdateMasterFile {
	
	public void updatePom(String filePath,String fileName){
		 File xmlFile = new File(filePath);
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            
	            //update Element value
	            updateElementValue(doc,fileName);
	  
	            //write the updated document to file or console
	            doc.getDocumentElement().normalize();
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(filePath));
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.transform(source, result);
	            System.out.println("XML file updated successfully");
	            
	        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
	            e1.printStackTrace();
	        }
		
		
	}
	 private static void updateElementValue(Document doc,String InputValue) {
	        NodeList employees = doc.getElementsByTagName("item");
	        Element emp = null;
	        //loop for each employee
	        for(int i=0; i<employees.getLength();i++){
	            emp = (Element) employees.item(i);
	            Node name = emp.getElementsByTagName("destination").item(0).getFirstChild();
	            name.setNodeValue(InputValue);
	        }
	    }
}
