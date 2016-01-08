package priv.susu.linkbook.chat.record;

public interface MessageConstant {

	/**
	 * 用户身份：房主
	 */
	public static final int USER_TYPE_CREATOR = 2;

	/**
	 * 用户身份：管理员
	 */
	public static final int USER_TYPE_MANAGER = 1;

	/**
	 * 用户身份：普通用户
	 */
	public static final int USER_TYPE_NORMAL = 0;

	/**
	 * 聊天消息范围：私聊
	 */
	public static final int MESSAGE_SCOPE_PERSON = 1;

	/**
	 * 聊天消息范围：群聊
	 */
	public static final int MESSAGE_SCOPE_TEAM = 2;

}