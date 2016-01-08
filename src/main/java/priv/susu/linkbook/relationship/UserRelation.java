package priv.susu.linkbook.relationship;

import java.io.Serializable;
import java.util.Date;

public class UserRelation implements Serializable {

	private static final long serialVersionUID = 1707997263161063646L;

	public static final UserRelation NULL;

	static {
		NULL = new UserRelation();
	}

	private int id = -1;

	private long localUid = -1;

	private long remoteUid = -1;

	private long fromUid = -1;

	private long toUid = -1;

	private int state = UserRelationConstant.STATE_APPLYING;

	private String memo = "";

	private Date createdTime = null;

	private Date updatedTime = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		checkNullObject();
		this.id = id;
	}

	public long getLocalUid() {
		return localUid;
	}

	public void setLocalUid(long localUid) {
		checkNullObject();
		this.localUid = localUid;
	}

	public long getRemoteUid() {
		return remoteUid;
	}

	public void setRemoteUid(long remoteUid) {
		checkNullObject();
		this.remoteUid = remoteUid;
	}

	public long getFromUid() {
		return fromUid;
	}

	public void setFromUid(long fromUid) {
		checkNullObject();
		this.fromUid = fromUid;
	}

	public long getToUid() {
		return toUid;
	}

	public void setToUid(long toUid) {
		checkNullObject();
		this.toUid = toUid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		checkNullObject();
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		checkNullObject();
		this.memo = memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		checkNullObject();
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		checkNullObject();
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "UserRelation [id=" + id + ", localUid=" + localUid + ", remoteUid=" + remoteUid + ", fromUid=" + fromUid + ", toUid=" + toUid + ", state=" + state + ", memo=" + memo
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

	private void checkNullObject() {
		if (this == NULL) {
			throw new UnsupportedOperationException("Could not modify NULL object.");
		}
	}
}
