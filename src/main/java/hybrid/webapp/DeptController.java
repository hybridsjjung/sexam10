package hybrid.webapp;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// COC (Convention Over Configuration)
@Controller
@RequestMapping("/dept")
public class DeptController {

	static Log log = LogFactory.getLog(DeptController.class);
	
	List<String> colors;

	public void setColors(List<String> colors) {
		
		this.colors = colors;
	}
	
	// http://localhost:8080/sexam10/employee/dept/select
	
	@RequestMapping("/select")
	public String select(Model model) {

		log.info("####################");
		log.info("select()");
		log.info("####################");

		model.addAttribute("colors", colors);
		
		return "dept/select";
	}
	
	// http://localhost:8080/sexam10/employee/dept/selectall
		
	@RequestMapping("/selectall")
	public String selectAll(Model model) {
		
		log.info("####################");
		log.info("selectall()");
		log.info("####################");
		
		String[] tzs = TimeZone.getAvailableIDs();
		model.addAttribute("tzs", tzs);

		return "dept/selectall";
	}
	
}
