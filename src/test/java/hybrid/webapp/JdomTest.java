package hybrid.webapp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.core.io.ClassPathResource;

// xml 읽기
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdomTest {

	static Log log = LogFactory.getLog(JdomTest.class);
	
	@Test
	public void test1_xmlprint() throws IOException {
		
		log.info("######### test1_xmlprint() ##########");	

		ClassPathResource xml = new ClassPathResource("/ipaddress.xml");
		InputStream in = xml.getInputStream();
		
		Scanner scan = new Scanner(in);
		while(scan.hasNextLine()) {
			log.info(scan.nextLine());	
		}
		
	}
	
	@Test
	public void test2_jdomparse() throws IOException, JDOMException {
	
		log.info("######### test2_jdomparse() ##########");	
		
		SAXBuilder jdom = new SAXBuilder();
		InputStream in = new ClassPathResource("/ipaddress.xml").getInputStream();
		
		Document doc = jdom.build(in);
		Element ipaddress = doc.getRootElement(); // RootElement - > ipaddress
		log.info("rootElement : " + ipaddress.getName());
		
		
		List<Element> persons = ipaddress.getChildren();
		for(Element e : persons) {
			log.info(e.getName() + ", " + e.getAttributeValue("id"));
			
			Element ip = e.getChild("ip");
			log.info(ip.getText());
			
			Element name = e.getChild("name");
			log.info(name.getText());
		}
		
	}

	@Test
	public void test3_jdomparse() throws IOException, JDOMException {
		
		log.info("######### test3_jdomparse() ##########");	
		
		SAXBuilder jdom = new SAXBuilder();
		InputStream in = new ClassPathResource("/spring-mvc.xml").getInputStream();
		
		Document doc = jdom.build(in);
		Element beans = doc.getRootElement(); // RootElement - > Beans
		log.info("rootElement : " + beans.getName());
		
		String cName = "org.springframework.web.servlet.view.InternalResourceViewResolver";
		
		List<Element> bean = beans.getChildren();
		for(Element e : bean) {
			if(e.getName().equals("bean") 
				&& e.getAttributeValue("class").equals(cName)) {
				log.info(e.getName() + ", " + e.getAttributeValue("class"));
				
				List<Element> property = e.getChildren();
				for(Element ee : property)
					log.info(ee.getAttributeValue("name") + ", " + ee.getAttributeValue("value"));
			}
		}

	}

	@Test
	public void test4_jdomparse() throws IOException, JDOMException {
	
		log.info("######### test4_jdomparse() ##########");	
		
		SAXBuilder jdom = new SAXBuilder();
		InputStream in = new ClassPathResource("/spring-mvc.xml").getInputStream();
		
		Document doc = jdom.build(in);
		Element beans = doc.getRootElement(); // RootElement - > Beans
		log.info("rootElement : " + beans.getName());
		
		String cName = "hybrid.webapp.DeptController";
		
		List<Element> bean = beans.getChildren();
		for(Element e : bean) {
			if(e.getName().equals("bean") 
				&& e.getAttributeValue("class").equals(cName)) {
				log.info(e.getName() + ", " + e.getAttributeValue("class"));
				
				List<Element> property = e.getChildren();
				for(Element ee : property) {
					log.info(ee.getName() + ", " + ee.getAttributeValue("name"));
						
					List<Element> list = ee.getChildren();
					for(Element eee : list) {
						
						List<Element> value = eee.getChildren();
						for(Element eeee : value)
							log.info(eee.getName() + ", " + eeee.getText());

					}

				}
			}
		}

	}
	
}
