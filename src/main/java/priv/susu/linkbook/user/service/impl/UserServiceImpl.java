package priv.susu.linkbook.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.dao.UserDao;
import priv.susu.linkbook.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public boolean isExist(String username) {
		return userDao.get(username) != User.NULL;
	}

	@Override
	public User get(long uid) {
		return userDao.get(uid);
	}

	@Override
	public List<User> getList(Collection<Long> uids) {
		List<User> result = new ArrayList<User>();
		if (uids == null || uids.isEmpty()) {
			return result;
		}
		for (Long uid : uids) {
			result.add(get(uid));
		}
		return result;
	}
}
