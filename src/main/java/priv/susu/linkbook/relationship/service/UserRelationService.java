package priv.susu.linkbook.relationship.service;

import java.util.List;

import priv.susu.linkbook.relationship.UserRelation;
import priv.susu.linkbook.user.User;

public interface UserRelationService {

	public void addFriend(long fromUid, long toUid, String memo);

	public void applyPass(long fromUid, long toUid);

	public void applyReject(long fromUid, long toUid);

	public void delete(long localUid, long remoteUid);

	public List<User> getApplyList(long uid);

	public List<User> getFriendList(long uid);
	
	public UserRelation get(long localUid, long remoteUid);

}
