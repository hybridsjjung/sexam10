package hybrid.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//xml 읽기
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlickrTest {

	static Log log = LogFactory.getLog(FlickrTest.class);
	String flickrurl = "https://api.flickr.com/services/feeds/photos_public.gne?tag=dog&tagmode=all";
	
	@Test
	public void test1_flickget() throws IOException {
		
		log.info("********** test1_flickget **********");	
		
		URL url = new URL(flickrurl);
		InputStream in = url.openStream();
		
		Scanner scan = new Scanner(in);
		while(scan.hasNextLine()) {
			log.info(scan.nextLine());
		}
	}

	@Test
	public void test2_flickParsing() throws IOException, JDOMException {

		log.info("********** test2_flickParsing() **********");	
		
		URL url = new URL(flickrurl);
		InputStream in = url.openStream();
		
		SAXBuilder jdom = new SAXBuilder();
		Document doc = jdom.build(in);
		
		Element feed = doc.getRootElement(); // RootElement - > feed
		log.info("rootElement : " + feed.getName());
		
		
		List<Element> childes = feed.getChildren();
		List<Element> entrys = new ArrayList<Element>();
		
		for(Element e: childes) {
			if(e.getName() == "entry")
				entrys.add(e);
		}		
		log.info("childes size :" + childes.size());
		log.info("entry size :" + entrys.size());
		
		
		List<Element> links = new ArrayList<Element>();
		for(Element e : entrys) {
			List<Element> link = e.getChildren();
			for(Element l : link) {
				if(l.getName().equals("link") && l.getAttributeValue("type").equals("image/jpeg"))
					links.add(l);
			}
		}
		
		log.info("links size :" + links.size());
		
		for(Element e : links) {
			log.info(e.getAttributeValue("href"));
		}
			
	}
	
	@Test
	public void test3_flickParsing() throws IOException, JDOMException {
		
		log.info("********** test3_flickParsing() **********");	
		
		URL url = new URL(flickrurl);
		InputStream in = url.openStream();
		
		SAXBuilder jdom = new SAXBuilder();
		Document document = jdom.build(in);
		
		XPathFactory pfactory = XPathFactory.instance();
		Namespace namespace = Namespace.getNamespace("f", "http://www.w3.org/2005/Atom");
		
		//XPathExpression<Element> expr = pfactory.compile("//f:entry", Filters.element(), null, namespace);
		//XPathExpression<Element> expr = pfactory.compile("/f:feed/f:entry", Filters.element(), null, namespace);
		//List<Element> entrys = expr.evaluate(document);
		//log.info("entry size = " + entrys.size());
		XPathExpression<Element> expr = pfactory.compile("//f:link", Filters.element(), null, namespace);
		
		List<Element> entrys = expr.evaluate(document);
		log.info("link size = " + entrys.size());
	
		for(Element e : entrys) {
			if("image/jpeg".equals(e.getAttributeValue("type")))
				log.info(e.getAttributeValue("type") + " = " + e.getAttributeValue("href"));
		}
	
	}

}
