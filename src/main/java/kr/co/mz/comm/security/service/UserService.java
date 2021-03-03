package kr.co.mz.comm.security.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mz.comm.security.dao.UserDAO;
import kr.co.mz.comm.security.model.UserVO;

@Service("userService")
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDAO;
	
	public UserVO findByusername(String username) {
		logger.debug("UserService.findByusername");
		UserVO userVO = null;
		try {
			userVO = userDAO.findByusername(username);
		} catch(SQLException sqlE) {
			//sqlE.printStackTrace();
		} finally {}
		
		return userVO;
	}
}
