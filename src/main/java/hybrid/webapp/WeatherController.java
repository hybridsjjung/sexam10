package hybrid.webapp;

import java.io.IOException;
import java.util.List;

import hybrid.webapp.service.FlickrService;
import hybrid.webapp.service.WeatherService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8080/sexam10/employee/weather/seoul

@Controller
@RequestMapping("/weather")
public class WeatherController {

	static Log log = LogFactory.getLog(WeatherController.class);
	
	WeatherService weather;
	
	public void setWeather(WeatherService weather) {
		this.weather = weather;
	}
	
	@RequestMapping("/seoul")
	public String seoul(Model model) throws IOException, JDOMException {
	
		log.info("##############################");
		log.info("seoul()...");
		log.info("##############################");
		
		List<Element> list = weather.queryImage("seoul");
		model.addAttribute("seoulcity", list);
		
		return "/weather/seoul";
	}
}
