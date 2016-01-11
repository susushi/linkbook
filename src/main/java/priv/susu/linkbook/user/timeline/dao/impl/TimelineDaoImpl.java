package priv.susu.linkbook.user.timeline.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import priv.susu.linkbook.user.timeline.Timeline;
import priv.susu.linkbook.user.timeline.dao.TimelineDao;

@Repository
public class TimelineDaoImpl implements TimelineDao {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void add(Timeline timeline) {
		Date date = new Date();
		timeline.setCreatedTime(date);
		timeline.setUpdatedTime(date);
		sqlSessionTemplate.insert("TimelineDao.insert", timeline);
	}

	@Override
	public void updateState(long id, int state) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("state", state);
		map.put("updatedTime", new Date());
		sqlSessionTemplate.update("TimelineDao.updateState", map);
	}

	@Override
	public int getCount(long uid) {
		Integer count = sqlSessionTemplate.selectOne("TimelineDao.getCount", uid);
		return count == null ? 0 : count;
	}

	@Override
	public List<Timeline> getFlowList(long uid, String breakpoint, int size) {
		long id = Long.MAX_VALUE;
		if(!StringUtils.isBlank(breakpoint)){
			id = Long.parseLong(breakpoint);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("uid", uid);
		map.put("size", size);
		return sqlSessionTemplate.selectList("TimelineDao.getFlowList", map);
	}

}
