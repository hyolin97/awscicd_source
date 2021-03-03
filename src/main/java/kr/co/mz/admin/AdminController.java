package kr.co.mz.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mz.comm.security.model.UserVO;

@Controller(value = "AdminController")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin/adminhome", method=RequestMethod.GET)
	public ModelAndView rootctr(HttpServletRequest req) throws Exception {
		logger.debug("/admin/adminhome");
		ModelAndView mav = new ModelAndView("admin/adminhome");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/memberList", method=RequestMethod.GET)
	public ModelAndView admin_memberList(HttpServletRequest req) throws Exception {
		logger.debug("/admin/memberList");
		ModelAndView mav = new ModelAndView();
		
		List<UserVO> memberList = adminService.memberList();
		
		mav.addObject("res", memberList);
		mav.setViewName("jsonView");
		return mav;
	}
}
