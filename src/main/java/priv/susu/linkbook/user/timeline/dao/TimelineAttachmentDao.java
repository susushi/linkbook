package priv.susu.linkbook.user.timeline.dao;

import java.util.List;

import priv.susu.linkbook.user.timeline.TimelineAttachment;

public interface TimelineAttachmentDao {

	public void add(TimelineAttachment attachment);
	
	public void updateState(long id, int state);
	
	public List<TimelineAttachment> getList(long timelineId);

}
