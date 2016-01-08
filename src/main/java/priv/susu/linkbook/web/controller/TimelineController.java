package priv.susu.linkbook.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import priv.susu.linkbook.user.timeline.Timeline;
import priv.susu.linkbook.user.timeline.TimelineAttachment;
import priv.susu.linkbook.web.Response;

@RequestMapping
public class TimelineController extends AbstractController {

	public Response<Integer> add(HttpServletRequest request, @RequestParam("content") String content, @RequestParam(value = "attachments", defaultValue = "", required = false) String[] attachments) {
		Response<Integer> response = new Response<Integer>();
		long uid = getCurrentUid(request);
		if(uid == -1){
			response.setCode(403);
			response.setMessage("请登录");
			return response;
		}
		Timeline timeline = new Timeline();
		timeline.setContent(content);
		timeline.setUid(getCurrentUid(request));
		timeline.setAttachments(getAttachments(attachments));
		return response;
	}

	private List<TimelineAttachment> getAttachments(String[] attachments) {
		List<TimelineAttachment> result = new ArrayList<TimelineAttachment>();
		for (int i = 0; i < attachments.length; i++) {
			if (StringUtils.isBlank(attachments[i]))
				continue;
			TimelineAttachment attachment = new TimelineAttachment(); // 默认图片类型
			attachment.setUrl(attachments[i]);
			result.add(attachment);
		}
		return result;
	}

}