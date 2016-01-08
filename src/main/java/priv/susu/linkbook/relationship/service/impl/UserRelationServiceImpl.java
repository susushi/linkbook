package priv.susu.linkbook.relationship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.susu.linkbook.relationship.UserRelation;
import priv.susu.linkbook.relationship.UserRelationConstant;
import priv.susu.linkbook.relationship.dao.UserRelationDao;
import priv.susu.linkbook.relationship.service.UserRelationService;
import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.service.UserService;

@Service
public class UserRelationServiceImpl implements UserRelationService {

	@Autowired
	private UserRelationDao userRelationDao;

	@Autowired
	private UserService userService;

	@Override
	public void addFriend(long fromUid, long toUid, String memo) {
		UserRelation relation = get(fromUid, toUid);
		if (relation.getState() == UserRelationConstant.STATE_APPLY_PASS) {
			throw new IllegalArgumentException("can not repeat add same friend. fromUid: " + fromUid + ". toUid: " + toUid);
		}
		UserRelation userRelation = new UserRelation();
		relation.setFromUid(fromUid);
		relation.setToUid(toUid);
		relation.setMemo(memo);
		long[] uids = getSmallerUid(fromUid, toUid);
		relation.setLocalUid(uids[0]);
		relation.setRemoteUid(uids[1]);
		relation.setState(UserRelationConstant.STATE_APPLYING);
		if (relation == UserRelation.NULL) {
			userRelationDao.add(userRelation);
		} else if (relation.getState() == UserRelationConstant.STATE_APPLY_REJECT || relation.getState() == UserRelationConstant.STATE_DELETED) {
			userRelationDao.update(userRelation);
		}

	}

	@Override
	public void applyPass(long fromUid, long toUid) {
		long[] uids = getSmallerUid(fromUid, toUid);
		userRelationDao.updateState(uids[0], uids[1], UserRelationConstant.STATE_APPLY_PASS);
	}

	@Override
	public void applyReject(long fromUid, long toUid) {
		long[] uids = getSmallerUid(fromUid, toUid);
		userRelationDao.updateState(uids[0], uids[1], UserRelationConstant.STATE_APPLY_REJECT);
	}

	@Override
	public void delete(long localUid, long remoteUid) {
		long[] uids = getSmallerUid(localUid, remoteUid);
		userRelationDao.updateState(uids[0], uids[1], UserRelationConstant.STATE_DELETED);
	}

	@Override
	public List<User> getApplyList(long uid) {
		List<Long> uids = userRelationDao.getApplyUidList(uid);
		return userService.getList(uids);
	}

	@Override
	public List<User> getFriendList(long uid) {
		List<Long> uids = userRelationDao.getRemoteUidList(uid);
		return userService.getList(uids);
	}

	@Override
	public UserRelation get(long localUid, long remoteUid) {
		if (localUid == remoteUid) {
			throw new IllegalArgumentException("args illegal. localUid: " + localUid + ". remoteUid: " + remoteUid);
		}
		long[] uids = getSmallerUid(localUid, remoteUid);
		UserRelation userRelation = null;
		userRelation = userRelationDao.get(uids[0], uids[1]);
		return userRelation;
	}

	private static long[] getSmallerUid(long uid1, long uid2) {
		long uids[] = new long[2];
		if (uid1 > uid2) {
			long temp = uid1;
			uid1 = uid2;
			uid2 = temp;
		}
		uids[0] = uid1;
		uids[1] = uid2;
		return uids;
	}

}
