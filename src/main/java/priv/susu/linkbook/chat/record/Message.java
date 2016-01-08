package priv.susu.linkbook.chat.record;

import priv.susu.linkbook.user.User;

public class Message {

	/**
	 * 消息ID
	 */
	private long id = -1;

	/**
	 * 消息范围
	 */
	private int scope = MessageConstant.MESSAGE_SCOPE_PERSON;

	/**
	 * 消息发送者是否为房间管理员
	 */
	private int identity = MessageConstant.USER_TYPE_NORMAL;

	/**
	 * 消息发送用户
	 */
	private User user = new User();

	/**
	 * 消息内容
	 */
	private String content = "";

	/**
	 * 消息发送时间（毫秒）
	 */
	private long createdTime = -1;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", scope=" + scope + ", identity=" + identity + ", user=" + user + ", content="
				+ content + ", createdTime=" + createdTime + "]";
	}

}