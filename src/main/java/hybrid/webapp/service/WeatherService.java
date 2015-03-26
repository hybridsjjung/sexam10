package hybrid.webapp.service;

import hybrid.webapp.FlickrTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

	SAXBuilder jdom;
	String weather = "http://weather.service.msn.com/data.aspx?culture=ko-KR&weasearchstr=%EC%84%9C%EC%9A%B8";

	static Log log = LogFactory.getLog(WeatherService.class);
	
	public WeatherService() {
		jdom = new SAXBuilder();
	}
	
	public List<Element> queryImage(String tag) throws IOException, JDOMException {
		
		List<String> list = new ArrayList<String>();
		URL url = new URL(weather);
		InputStream in = url.openStream();
		Document document = jdom.build(in);

		Element root = document.getRootElement(); // RootElement - > weatherdata
		log.info("rootElement : " + root.getName());
		
		List<Element> childes = root.getChildren();
		List<Element> lists = new ArrayList<Element>();
		
		for(Element e: childes) {
			log.info("childesElement : " + e.getName());
			
			if(e.getName() == "weather")
				lists.add(e);
		}	
		
		
		List<Element> weather = new ArrayList<Element>();
		for(Element e : lists) {
			List<Element> current = e.getChildren();
			for(Element l : current) {
				if(l.getName().equals("current") || l.getName().equals("forecast")) 
					weather.add(l);
			}
		}
		
		return weather;
				
	}
}
