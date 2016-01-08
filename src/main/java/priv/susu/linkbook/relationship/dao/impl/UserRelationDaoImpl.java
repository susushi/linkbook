package priv.susu.linkbook.relationship.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.susu.linkbook.relationship.UserRelation;
import priv.susu.linkbook.relationship.dao.UserRelationDao;

@Repository
public class UserRelationDaoImpl implements UserRelationDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void add(UserRelation userRelation) {
		Date date = new Date();
		userRelation.setCreatedTime(date);
		userRelation.setUpdatedTime(date);
		sqlSessionTemplate.insert("UserRelationDao.insert", userRelation);
	}

	@Override
	public UserRelation get(long localUid, long remoteUid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("localUid", localUid);
		params.put("remoteUid", remoteUid);
		UserRelation userRelation = sqlSessionTemplate.selectOne("UserRelationDao.get", params);
		return userRelation == null ? UserRelation.NULL : userRelation;
	}

	@Override
	public void updateState(long localUid, long remoteUid, int state) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("localUid", localUid);
		params.put("remoteUid", remoteUid);
		params.put("state", state);
		params.put("updatedTime", new Date());
		sqlSessionTemplate.update("UserRelationDao.updateState", params);
	}

	@Override
	public List<Long> getRemoteUidList(long localUid) {
		return sqlSessionTemplate.selectList("UserRelationDao.getRemoteUidList", localUid);
	}

	@Override
	public List<Long> getApplyUidList(long toUid) {
		return sqlSessionTemplate.selectList("UserRelationDao.getApplyUidList", toUid);
	}

	@Override
	public void update(UserRelation relation) {
		relation.setUpdatedTime(new Date());
		sqlSessionTemplate.update("UserRelationDao.update", relation);
	}

}
