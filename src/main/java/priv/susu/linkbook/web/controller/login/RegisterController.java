package priv.susu.linkbook.web.controller.login;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.user.email.MailConstant;
import priv.susu.linkbook.user.email.service.MailService;
import priv.susu.linkbook.user.service.UserService;
import priv.susu.linkbook.utils.MD5Util;
import priv.susu.linkbook.web.Response;
import priv.susu.linkbook.web.WebAttributes;
import priv.susu.linkbook.web.controller.AbstractController;

@Controller
@RequestMapping(value = "/register")
public class RegisterController extends AbstractController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@RequestMapping(method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public Response<Integer> register(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam(value = "gender",required = false, defaultValue = "1") int gender, @RequestParam("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate) {
		Response<Integer> response = new Response<Integer>();
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
		if (StringUtils.isEmpty(firstname) || StringUtils.isEmpty(lastname)) {
			response.setCode(1104);
			response.setMessage("姓名不能为空");
			return response;
		}
		if (userService.isExist(username)) {
			response.setCode(1103);
			response.setMessage("邮箱已经被注册");
			return response;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5Util.MD5(password));
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setGender(gender);
		user.setBirthDate(birthdate);
		userService.add(user);
		// 发送验证邮件
		mailService.sendWelcomeMail(user);
		request.setAttribute(WebAttributes.REQUEST_CURRENT_USER, user);
		return response;
	}

}
