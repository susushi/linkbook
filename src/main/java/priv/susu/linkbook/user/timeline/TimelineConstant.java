package priv.susu.linkbook.user.timeline;

public interface TimelineConstant {

	/**
	 * 动态状态：删除
	 */
	public static final int TIMELINE_STATE_DELETED = -1;

	/**
	 * 动态状态：正常
	 */
	public static final int TIMELINE_STATE_NORMAL = 0;
	
	/**
	 * 动态附件状态：删除
	 */
	public static final int TIMELINE_ATTACHMENT_STATE_DELETED = -1;

	/**
	 * 动态附件状态：正常
	 */
	public static final int TIMELINE_ATTACHMENT_STATE_NORMAL = 0;

	/**
	 * 附件类型：图片
	 */
	public static final int TIMELINE_ATTACHMENT_TYPE_PIC = 1;

	/**
	 * 附件类型：音乐
	 */
	public static final int TIMELINE_ATTACHMENT_TYPE_MUSIC = 2;

	/**
	 * 附件类型：其他
	 */
	public static final int TIMELINE_ATTACHMENT_TYPE_OTHER = 3;

}