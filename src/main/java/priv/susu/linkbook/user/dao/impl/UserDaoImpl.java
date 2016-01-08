package priv.susu.linkbook.user.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public User add(User user) {
		Date date = new Date();
		user.setRegisterTime(date);
		user.setLastLoginTime(date);
		sqlSessionTemplate.insert("UserDao.insert", user);
		return user;
	}

	@Override
	public User get(String username) {
		User user = sqlSessionTemplate.selectOne("UserDao.getByUsername", username);
		return user == null ? User.NULL : user;
	}

	@Override
	public User get(long uid) {
		User user = sqlSessionTemplate.selectOne("UserDao.get", uid);
		return user == null ? User.NULL : user;
	}

}
