package priv.susu.linkbook.user.timeline.service;

import java.util.List;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.user.timeline.Timeline;
import priv.susu.linkbook.user.timeline.TimelineAttachment;

public interface TimelineService {

	public void add(Timeline timeline, List<TimelineAttachment> attachments);
	
	public void delete(long id);
	
	public FlowWrapper<Timeline> getFlow(long uid, String breakpoint, int size);
	
}
