package priv.susu.linkbook.user.timeline.service;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.user.timeline.Timeline;

public interface TimelineService {

	public void add(Timeline timeline);
	
	public void delete(long id);
	
	public FlowWrapper<Timeline> getFlow(long uid, String breakpoint, int size);
	
}
