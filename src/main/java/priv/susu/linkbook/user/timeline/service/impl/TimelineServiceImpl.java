package priv.susu.linkbook.user.timeline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.user.timeline.Timeline;
import priv.susu.linkbook.user.timeline.TimelineAttachment;
import priv.susu.linkbook.user.timeline.TimelineConstant;
import priv.susu.linkbook.user.timeline.dao.TimelineDao;
import priv.susu.linkbook.user.timeline.service.TimelineAttachmentService;
import priv.susu.linkbook.user.timeline.service.TimelineService;

@Service
public class TimelineServiceImpl implements TimelineService {

	@Autowired
	private TimelineDao timelineDao;

	@Autowired
	private TimelineAttachmentService timelineAttachmentService;

	@Override
	public void add(Timeline timeline, List<TimelineAttachment> attachments) {
		Timeline line = timelineDao.add(timeline);
		for (TimelineAttachment attachment : attachments) {
			attachment.setTimelineId(line.getId());
		}
		timelineAttachmentService.add(attachments);
	}

	@Override
	public void delete(long id) {
		timelineDao.updateState(id, TimelineConstant.TIMELINE_STATE_DELETED);
	}

	@Override
	public FlowWrapper<Timeline> getFlow(long uid, String breakpoint, int size) {
		FlowWrapper<Timeline> result = new FlowWrapper<Timeline>();
		result.setCount(timelineDao.getCount(uid));
		if (result.getCount() == 0) {
			return result;
		}
		List<Timeline> timelineList = timelineDao.getFlowList(uid, breakpoint, size);
		for (Timeline timeline : timelineList) {
			timeline.setAttachments(timelineAttachmentService.getList(timeline.getId()));
		}
		if (!timelineList.isEmpty()) {
			breakpoint = String.valueOf(timelineList.get(timelineList.size() - 1).getId());
		}
		result.setBreakpoint(breakpoint);
		result.setItems(timelineList);
		return result;
	}

}
