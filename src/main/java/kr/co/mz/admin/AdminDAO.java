package kr.co.mz.admin;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mz.comm.security.model.UserVO;

@Repository(value="AdminDAO")
public class AdminDAO {
	private static final Logger logger = LoggerFactory.getLogger(AdminDAO.class);
	
	private static final String SQL_PREFIX = "Admin.";
	
	@Autowired
	@Resource(name="sqlSession02")
	private SqlSessionTemplate sqlSession02;
	
	public List<UserVO> memberList() throws SQLException {
		logger.debug("AdminDAO.memberList");
		return sqlSession02.selectList(SQL_PREFIX+"memberList");
	}
}
