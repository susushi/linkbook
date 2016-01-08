package priv.susu.linkbook.web.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.web.WebAttributes;

public abstract class AbstractController {

	/**
	 * 重定向至错误页面，并展示指定的错误信息
	 * 
	 * @param message
	 * @return 重定向视图命令
	 */
	protected String redirectToErrorPage() {
		return "redirect:/error.htm";
	}

	/**
	 * 将请求所带的参数转成String用于展现
	 * 
	 * @param request
	 * @return 请求所带的参数转成String
	 */
	protected String getParamString(HttpServletRequest request) {
		StringBuilder buffer = new StringBuilder();
		@SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = request.getParameterMap();
		for (String paramName : paramMap.keySet()) {
			String paramValue = Arrays.toString(paramMap.get(paramName));
			buffer.append(paramName).append("=").append(paramValue).append("&");
		}
		return buffer.toString();
	}

	/**
	 * 获取当前用户, 当没有当前用户信息时, 返回{@link User#NULL}
	 * <p>
	 * 该接口不会返回{@code null}
	 * 
	 * @param request
	 *            请求信息
	 * @return 当前用户信息
	 */
	protected User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(WebAttributes.REQUEST_CURRENT_USER);
		return currentUser != null ? currentUser : User.NULL;
	}

	protected long getCurrentUid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(WebAttributes.REQUEST_CURRENT_USER);
		return currentUser != null ? currentUser.getId() : -1;
	}

}