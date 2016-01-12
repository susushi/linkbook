package priv.susu.linkbook.user.timeline.service;

import java.util.List;

import priv.susu.linkbook.user.timeline.TimelineAttachment;

public interface TimelineAttachmentService {

	public void add(List<TimelineAttachment> attachments);
	
	public void delete(long id);
	
	public List<TimelineAttachment> getList(long timelineId);
	
}
