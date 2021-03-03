package kr.co.mz.comm.security.dao;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mz.comm.security.model.UserVO;

@Repository("userDAO")
public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	private static final String SQL_PREFIX = "SpringSecurity.";
	
	@Autowired
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	public UserVO findByusername(String username) throws SQLException {
		logger.debug("UserDAO.findByusername");
		return sqlSession.selectOne(SQL_PREFIX+"findByusername", username);
	}
}
