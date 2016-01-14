package priv.susu.linkbook.user.timeline.dao;

import java.util.List;

import priv.susu.linkbook.user.timeline.Timeline;

public interface TimelineDao {

	public Timeline add(Timeline timeline);
	
	public void updateState(long id, int state);
	
	public int getCount(long uid);

	public List<Timeline> getFlowList(long uid, String breakpoint, int size);

}
