package priv.susu.linkbook.user.timeline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timeline implements Serializable {

	private static final long serialVersionUID = -4409097552126429418L;

	/**
	 * 主键ID
	 */
	private long id = -1;

	/**
	 * 动态所属的用户ID
	 */
	private long uid = -1;

	/**
	 * 动态内容
	 */
	private String content = "";

	/**
	 * 动态的状态
	 */
	private int state = TimelineConstant.TIMELINE_STATE_NORMAL;

	/**
	 * 动态创建的时间
	 */
	private Date createdTime = null;

	/**
	 * 动态修改的时间
	 */
	private Date updatedTime = null;

	/**
	 * 附件列表
	 */
	private List<TimelineAttachment> attachments = new ArrayList<TimelineAttachment>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<TimelineAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TimelineAttachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Timeline [id=" + id + ", uid=" + uid + ", content=" + content + ", state=" + state + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", attachments=" + attachments
				+ "]";
	}

}
