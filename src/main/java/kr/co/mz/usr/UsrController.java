package kr.co.mz.usr;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "UsrController")
public class UsrController {
	private static final Logger logger = LoggerFactory.getLogger(UsrController.class);
	
	@RequestMapping(value="/usr/usrhome", method=RequestMethod.GET)
	public ModelAndView rootctr(HttpServletRequest req) throws Exception {
		logger.debug("/usr/usrhome");
		ModelAndView mav = new ModelAndView("usr/usrhome");
		
		return mav;
	}
}
