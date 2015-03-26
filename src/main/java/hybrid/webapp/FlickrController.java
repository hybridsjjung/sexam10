package hybrid.webapp;

import java.io.IOException;
import java.util.List;

import hybrid.webapp.service.FlickrService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8080/sexam10/employee/flickr/dog

@Controller
@RequestMapping("/flickr")
public class FlickrController {

	static Log log = LogFactory.getLog(FlickrController.class);
	
	FlickrService service;
	
	public void setService(FlickrService service) {
		this.service = service;
	}
	
	@RequestMapping("/dog")
	public String dog(Model model) throws IOException, JDOMException {
	
		log.info("##############################");
		log.info("dog()...");
		log.info("##############################");
		
		List<String> list = service.queryImage("dog");
		model.addAttribute("dogs", list);
		
		return "/flickr/dog";
	}
}
