package priv.susu.linkbook.user.service;

import java.util.Collection;
import java.util.List;

import priv.susu.linkbook.user.User;

public interface UserService {

	public void add(User user);

	public User get(String username);

	public boolean isExist(String username);

	public User get(long uid);
	
	public List<User> getList(Collection<Long> uids);

}
