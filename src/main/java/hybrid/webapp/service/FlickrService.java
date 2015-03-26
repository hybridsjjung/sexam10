package hybrid.webapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
public class FlickrService {

	SAXBuilder jdom;
	XPathFactory xfactory;
	XPathExpression<Element> expr;
	
	String flickrurl = "https://api.flickr.com/services/feeds/photos_public.gne?tag=dog&tagmode=all";
	
	public FlickrService() {
		jdom = new SAXBuilder();
		xfactory = XPathFactory.instance();
		Namespace namespace = Namespace.getNamespace("f", "http://www.w3.org/2005/Atom");
		expr = xfactory.compile("//f:link[@type='image/jpeg']", Filters.element(), null, namespace);
	}
	
	public List<String> queryImage(String tag) throws IOException, JDOMException {
		
		List<String> list = new ArrayList<String>();
		URL url = new URL(flickrurl);
		InputStream in = url.openStream();
		Document document = jdom.build(in);
		
		List<Element> links = expr.evaluate(document);
		
		for(Element e : links) {
			list.add(e.getAttributeValue("href"));
		}
		
		return list;
				
	}
}
