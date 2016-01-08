package priv.susu.linkbook.web.controller.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.email.MailConstant;
import priv.susu.linkbook.user.service.UserService;
import priv.susu.linkbook.utils.MD5Util;
import priv.susu.linkbook.web.Response;
import priv.susu.linkbook.web.WebAttributes;
import priv.susu.linkbook.web.controller.AbstractController;

@Controller
public class LoginController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Response<String> submit(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
		Response<String> response = new Response<String>();
		Pattern regex = Pattern.compile(MailConstant.CHECK_EMAIL_REGEX);
		Matcher matcher = regex.matcher(username);
		if (!matcher.matches()) {
			response.setCode(1101);
			response.setMessage("邮箱格式不正确");
			return response;
		}
		if (StringUtils.isEmpty(password)) {
			response.setCode(1102);
			response.setMessage("密码不能为空");
			return response;
		}
		User user = userService.get(username);
		if (user == User.NULL) {
			response.setCode(1103);
			response.setMessage("用户名或密码错误");
			return response;
		}
		if (!user.getPassword().equals(MD5Util.MD5(password))) {
			response.setCode(1103);
			response.setMessage("用户名或密码错误");
			return response;
		}
		session.setAttribute(WebAttributes.REQUEST_CURRENT_USER, user);
		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Response<Integer> logout(HttpSession session) {
		Response<Integer> response = new Response<Integer>();
		session.removeAttribute(WebAttributes.REQUEST_CURRENT_USER);
		return response;
	}

}
