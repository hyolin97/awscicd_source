package kr.co.mz.main;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "MainController")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/firstMain", method=RequestMethod.GET)
	public ModelAndView firstMain(HttpServletRequest req) throws Exception {
		logger.debug("/firstMain");
		ModelAndView mav = new ModelAndView("main/firstMain");
		
		
		return mav;
	}
	
	@RequestMapping(value="/secondMain", method=RequestMethod.GET)
	public ModelAndView secondMain(HttpServletRequest req) throws Exception {
		logger.debug("/secondMain");
		ModelAndView mav = new ModelAndView("main/secondMain");
		
		
		return mav;
	}

	@RequestMapping(value="/sitemesh1", method=RequestMethod.GET)
	public ModelAndView sitemesh1(HttpServletRequest req) throws Exception {
		logger.debug("/sitemesh1");
		ModelAndView mav = new ModelAndView("main/sitemesh1");
		
		
		return mav;
	}
	
	@RequestMapping(value="/sitemeshPop1.pop", method=RequestMethod.GET)
	public ModelAndView sitemeshPop1_pop(HttpServletRequest req) throws Exception {
		logger.debug("/sitemeshPop1.pop");
		ModelAndView mav = new ModelAndView("main/sitemeshPop1");
		
		
		return mav;
	}

	@RequestMapping(value="/sitemeshAjax1.ajax", method=RequestMethod.GET)
	public ModelAndView sitemeshAjax1_ajax(HttpServletRequest req) throws Exception {
		logger.debug("/sitemeshAjax1.ajax");
		ModelAndView mav = new ModelAndView("main/sitemeshAjax1");
		
		
		return mav;
	}
}
