package kr.co.mz.comm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.mz.comm.utils.session.SessionUtil;
import kr.co.mz.comm.utils.session.SessionVO;

public class InterceptorAdapter extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(InterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String url = request.getRequestURI();
		
		/***** 무시되어야 하는 URI                              *****/
		/***** /css/, /js/, /img/, /font/, /favicon.ico 등  *****/
		/***** 을 interceptor 무시 처리                                                    *****/
		if(url.contains("/css/")
				|| url.contains("/js/")
				|| url.contains("/img/")
				|| url.contains("/font/")
				|| url.contains("/favicon.ico")
				|| url.matches("/home")
				|| url.matches("/login")
				|| url.matches("/loginfail")
				) {
			// 기본적인 이미지/js/css 는 무시한다
			return true;
		}
		
		logger.debug("interceptor url=["+url+"]");
		if(url.matches("/")) {
			// root 이면 /home 으로 리다이렉트
			response.sendRedirect("/home");
			return false;
		}
		
		// 세션 검사
		HttpSession session = request.getSession();
		SessionVO sessionVO = SessionUtil.getSessionVO(session);
		
		if( sessionVO == null ) {
			logger.debug("session is null");
			SessionUtil.deleteSessionVO(session);
			
			response.sendRedirect("/home");
			return false;
		} else {
			logger.debug("session is not null");
			
		}
		return true;
	}
	
}
