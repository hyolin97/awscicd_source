package kr.co.mz.comm.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import kr.co.mz.comm.security.model.UserVO;
import kr.co.mz.comm.security.service.impl.CustomUserDetailsService;
import kr.co.mz.comm.utils.session.SessionUtil;

@Component("customFailureHandler")
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomFailureHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException exception) throws IOException, ServletException {
		logger.debug("onAuthenticationFailure");
		
		String username = request.getParameter("username");
		logger.debug("username=["+username+"]");
		
		String msId = null;
		UserVO userVO = CustomUserDetailsService.getAccountOne(username);
		if( userVO == null ) {
			// username 존재하지 않습니다.
			msId = "1";
		} else {
			// 패스워드가 일치하지 않습니다.
			msId = "2";
		}
		
		String targetUrl = "/loginfail?username="+username+"&msId="+msId;
		
		SessionUtil.deleteSessionVO(request.getSession());
		this.redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
