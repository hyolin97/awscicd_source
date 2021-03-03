package kr.co.mz.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.co.mz.comm.utils.session.SessionUtil;
import kr.co.mz.comm.utils.session.SessionVO;


@Controller(value = "HomeController")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView rootctr(HttpServletRequest req) throws Exception {
		logger.debug("/home");
		ModelAndView mav = new ModelAndView("home/home");
		
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req) throws Exception {
		logger.debug("/login");
		ModelAndView mav = new ModelAndView("home/login");
		
		return mav;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req) throws Exception {
		logger.debug("/logout");
		ModelAndView mav = new ModelAndView();
		
		SessionVO sessionVO = SessionUtil.getSessionVO(req.getSession());
		if( sessionVO != null ) {
			logger.debug("sessionVO."+sessionVO.getUser().getUsername());
			SessionUtil.deleteSessionVO(req.getSession());
			sessionVO = null;
		}
		logger.debug("sessionVO="+sessionVO);
		logger.debug("SessionUtil.getSessionVO(req.getSession())="+SessionUtil.getSessionVO(req.getSession()));
		
		RedirectView redirectView = new RedirectView("/login");
		mav.setView(redirectView);
		return mav;
	}
	
	@RequestMapping(value="/loginfail", method=RequestMethod.GET) 
	public ModelAndView loginfail(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		logger.debug("loginFail");
		ModelAndView mav = new ModelAndView();
		
		String message;
		String username = req.getParameter("username");
		String msId = req.getParameter("msId");
		logger.debug("username=["+username+"]");
		logger.debug("msId=["+msId+"]");
		
		if( "1".equals(msId) ) {
			// 
			message = "ID 가 존재하지 않습니다.";
		} else if( "2".equals(msId) ) {
			// 패스워드가 일치하지 않습니다.
			message = "패스워드가 일치하지 않습니다.";
		} else {
			// 일시적으로 로그인 할 수 없습니다.
			message = "일시적으로 로그인 할 수 없습니다.";
		}
		
		model.addAttribute("username", username);
		model.addAttribute("message", message);
		
		mav.setViewName("home/loginfail");
		return mav;
	}
}
