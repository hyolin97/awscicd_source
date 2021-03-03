package kr.co.mz.sadmin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "SAdminController")
public class SAdminController {
	private static final Logger logger = LoggerFactory.getLogger(SAdminController.class);
	
	@RequestMapping(value="/sadmin/sadminhome", method=RequestMethod.GET)
	public ModelAndView rootctr(HttpServletRequest req) throws Exception {
		logger.debug("/sadmin/sadminhome");
		ModelAndView mav = new ModelAndView("sadmin/sadminhome");
		
		return mav;
	}
}
