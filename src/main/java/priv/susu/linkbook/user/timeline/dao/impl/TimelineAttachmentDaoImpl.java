package priv.susu.linkbook.user.timeline.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.susu.linkbook.user.timeline.TimelineAttachment;
import priv.susu.linkbook.user.timeline.dao.TimelineAttachmentDao;

@Repository
public class TimelineAttachmentDaoImpl implements TimelineAttachmentDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void add(TimelineAttachment attachment) {
		Date date = new Date();
		attachment.setCreatedTime(date);
		attachment.setUpdatedTime(date);
		sqlSessionTemplate.insert("TimelineAttachmentDao.insert", attachment);
	}

	@Override
	public void updateState(long id, int state) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("state", state);
		map.put("updatedTime", new Date());
		sqlSessionTemplate.update("TimelineAttachmentDao.updateState", map);
	}

	@Override
	public List<TimelineAttachment> getList(long timelineId) {
		return sqlSessionTemplate.selectList("TimelineAttachmentDao.getList", timelineId);
	}

}
