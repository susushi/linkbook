package priv.susu.linkbook.user.dao;

import priv.susu.linkbook.user.User;

public interface UserDao {

	public User add(User user);

	public User get(String username);

	public User get(long uid);

}
