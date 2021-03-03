package kr.co.mz.comm.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import kr.co.mz.comm.security.model.UserVO;
import kr.co.mz.comm.security.service.impl.CustomUserDetailsService;
import kr.co.mz.comm.utils.session.SessionUtil;
import kr.co.mz.comm.utils.session.SessionVO;

@Component("customSuccessHandler")
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger("CustomSuccessHandler");
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		logger.debug("handle");
		
		String targetUrl = determineTargetUrl(authentication);
		
		if( response.isCommitted() ) {
			logger.debug("Can't redirect");
            return;
		}
		
		SessionVO sessionVO = new SessionVO();
		String username = authentication.getName();
		
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails)authentication.getDetails();
		String sessionId = webAuthenticationDetails.getSessionId();
		
		UserVO userVO = CustomUserDetailsService.getAccountOne(username);
		sessionVO.setUser(userVO);
		sessionVO.setSessionID(sessionId);
		
		SessionUtil.insertSessionVO(request.getSession(), sessionVO);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication) {
    	String url = "";
 
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
 
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if(roles != null) {
        	System.out.println(roles);
        }
 
        if (isSuperAdmin(roles)) {
        	url = "/sadmin/sadminhome";
        } else if (isAdmin(roles)) {
        	url = "/admin/adminhome";
        } else if (isUser(roles)) {
        	url = "/usr/usrhome";
        } else {
            url = "/accessDenied";
        }
 
        return url;
    }
	
    private boolean isSuperAdmin(List<String> roles) {
        if (roles.contains("ROLE_SUPERADMIN")) {
            return true;
        }
        return false;
    }
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
    private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
