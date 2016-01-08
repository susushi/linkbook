package priv.susu.linkbook.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.susu.linkbook.relationship.UserRelation;
import priv.susu.linkbook.relationship.UserRelationConstant;
import priv.susu.linkbook.relationship.service.UserRelationService;
import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.service.UserService;
import priv.susu.linkbook.web.Response;
import priv.susu.linkbook.web.annotation.RegisterUserUrl;
import priv.susu.linkbook.web.controller.AbstractController;

@Controller
@RequestMapping("/u/relation")
public class UserRelationController extends AbstractController {

	@Autowired
	private UserRelationService userRelationService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	@RegisterUserUrl
	public Response<Integer> apply(HttpServletRequest request, @RequestParam("uid") long toUid, @RequestParam(value = "memo", required = false, defaultValue = "") String memo) {
		Response<Integer> response = new Response<Integer>();
		User remoteUser = userService.get(toUid);
		if (remoteUser == User.NULL) {
			response.setCode(1101);
			response.setMessage("用户不存在");
			return response;
		}
		long fromUid = getCurrentUid(request);
		UserRelation relation = userRelationService.get(fromUid, toUid);
		if (relation != UserRelation.NULL) {
			if (relation.getState() == UserRelationConstant.STATE_APPLY_PASS) {
				response.setCode(1102);
				response.setMessage("该用户已在您的好友列表中");
				return response;
			}
			if (relation.getState() == UserRelationConstant.STATE_APPLYING && fromUid == relation.getFromUid()) {
				response.setCode(1103);
				response.setMessage("好友申请已提交");
				return response;
			}
			if (relation.getState() == UserRelationConstant.STATE_APPLYING && fromUid == relation.getToUid()) {
				response.setCode(1104);
				response.setMessage("该用户已向你提出好友申请");
				return response;
			}
		}
		userRelationService.addFriend(fromUid, toUid, memo);
		return response;
	}

	@RequestMapping(value = "/apply-pass", method = RequestMethod.POST)
	@ResponseBody
	@RegisterUserUrl
	public Response<Integer> applyPass(HttpServletRequest request, @RequestParam("uid") long uid) {
		Response<Integer> response = new Response<Integer>();
		long localUid = getCurrentUid(request);
		UserRelation relation = userRelationService.get(localUid, uid);
		if (relation == UserRelation.NULL) {
			response.setCode(1101);
			response.setMessage("该用户没有向你发起好友申请");
			return response;
		}
		if (relation.getState() == UserRelationConstant.STATE_APPLY_PASS) {
			response.setCode(1102);
			response.setMessage("该用户已在您的好友列表中");
			return response;
		}
		userRelationService.applyPass(localUid, uid);
		return response;
	}

	@RequestMapping(value = "/apply-reject", method = RequestMethod.POST)
	@ResponseBody
	@RegisterUserUrl
	public Response<Integer> applyReject(HttpServletRequest request, @RequestParam("uid") long uid) {
		Response<Integer> response = new Response<Integer>();
		long localUid = getCurrentUid(request);
		UserRelation relation = userRelationService.get(localUid, uid);
		if (relation == UserRelation.NULL) {
			response.setCode(1101);
			response.setMessage("该用户没有向你发起好友申请");
			return response;
		}
		if (relation.getState() == UserRelationConstant.STATE_APPLY_PASS) {
			response.setCode(1102);
			response.setMessage("该用户已在您的好友列表中");
			return response;
		}
		userRelationService.applyReject(localUid, uid);
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@RegisterUserUrl
	public Response<Integer> delete(HttpServletRequest request, @RequestParam("uid") long uid) {
		Response<Integer> response = new Response<Integer>();
		long localUid = getCurrentUid(request);
		UserRelation relation = userRelationService.get(localUid, uid);
		if (relation.getState() == UserRelationConstant.STATE_DELETED) {
			response.setCode(1102);
			response.setMessage("该用户不在您的好友列表中");
			return response;
		}
		userRelationService.delete(localUid, uid);
		return response;
	}

}
