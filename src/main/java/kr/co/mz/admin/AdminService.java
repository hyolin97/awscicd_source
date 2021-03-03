package kr.co.mz.admin;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mz.comm.security.model.UserVO;

@Service(value="AdminService")
public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private AdminDAO adminDAO;
	
	public List<UserVO> memberList() {
		logger.debug("AdminService.memberList");
		List<UserVO> userVOList = null;
		try {
			userVOList = adminDAO.memberList();
		} catch (SQLException sqle) {
			// error 처리
		} finally {
			// 
		}
		return userVOList;
	}
	
}
