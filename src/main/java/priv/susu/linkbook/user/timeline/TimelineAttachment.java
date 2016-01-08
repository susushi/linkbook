package priv.susu.linkbook.user.timeline;

import java.io.Serializable;
import java.util.Date;

public class TimelineAttachment implements Serializable {

	private static final long serialVersionUID = 6626298694068985694L;

	/**
	 * ID
	 */
	private int id = -1;

	/**
	 * 附件所属的留言板ID
	 */
	private long timelineId = -1;

	/**
	 * 附件类型
	 */
	private int type = TimelineConstant.TIMELINE_ATTACHMENT_TYPE_PIC;

	/**
	 * 附件地址
	 */
	private String url = "";

	/**
	 * 附件状态
	 */
	private int state = TimelineConstant.TIMELINE_STATE_NORMAL;

	/**
	 * 创建时间
	 */
	private Date createdTime = null;

	/**
	 * 更新时间
	 */
	private Date updatedTime = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(long timelineId) {
		this.timelineId = timelineId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "TimelineAttachment [id=" + id + ", timelineId=" + timelineId + ", type=" + type + ", url=" + url + ", state=" + state + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ "]";
	}

}