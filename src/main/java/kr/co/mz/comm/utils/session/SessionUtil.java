package kr.co.mz.comm.utils.session;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "SessionUtil")
public class SessionUtil {
	private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);
	
	/*
	 * getSessionVO
	 */
	public static SessionVO getSessionVO(HttpSession session) {
		logger.debug("SessionUtil.getSessionVO");
		
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		return sessionVO;
	}
	/*
	 * insert SessionVO
	 */
	public static void insertSessionVO(HttpSession session, SessionVO sessionVO) {
		logger.debug("SessionUtil.insertSessionVO");
		session.setAttribute("sessionVO", sessionVO);
	}
	/*
	 * delete SessionVO
	 */
	public static void deleteSessionVO(HttpSession session) {
		logger.debug("SessionUtil.deleteSessionVO");
		session.invalidate();
	}
}
