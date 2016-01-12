package priv.susu.linkbook.user.timeline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.susu.linkbook.user.timeline.TimelineAttachment;
import priv.susu.linkbook.user.timeline.TimelineConstant;
import priv.susu.linkbook.user.timeline.dao.TimelineAttachmentDao;
import priv.susu.linkbook.user.timeline.service.TimelineAttachmentService;

@Service
public class TimelineAttachmentServiceImpl implements TimelineAttachmentService {

	@Autowired
	private TimelineAttachmentDao timelineAttachmentDao;
	
	@Override
	public void add(List<TimelineAttachment> attachments) {
		if(attachments == null || attachments.isEmpty()){
			return;
		}
		for(TimelineAttachment attachment : attachments){
			timelineAttachmentDao.add(attachment);
		}
	}

	@Override
	public void delete(long id) {
		timelineAttachmentDao.updateState(id, TimelineConstant.TIMELINE_ATTACHMENT_STATE_DELETED);
	}

	@Override
	public List<TimelineAttachment> getList(long timelineId) {
		return timelineAttachmentDao.getList(timelineId);
	}

}
