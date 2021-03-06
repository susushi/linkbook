package priv.susu.linkbook.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import priv.susu.linkbook.FlowWrapper;
import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.service.UserService;
import priv.susu.linkbook.user.timeline.Timeline;
import priv.susu.linkbook.user.timeline.service.TimelineService;
import priv.susu.linkbook.web.WebAttributes;

@Controller
public class IndexController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private TimelineService timelineService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		User user = (User) session.getAttribute(WebAttributes.REQUEST_CURRENT_USER);
		if (user == null) {
			return "login";
		}
		FlowWrapper<Timeline> flow = timelineService.getFlow(user.getId(), "", 10);
		model.addAttribute("timelineList", flow);
		return "index";
	}

}
