package priv.susu.linkbook.relationship.dao;

import java.util.List;

import priv.susu.linkbook.relationship.UserRelation;

public interface UserRelationDao {

	public void add(UserRelation userRelation);

	public void updateState(long localUid, long remoteUid, int state);

	public UserRelation get(long localUid, long remoteUid);

	public List<Long> getRemoteUidList(long localUid);

	public List<Long> getApplyUidList(long toUid);

	public void update(UserRelation relation);

}
