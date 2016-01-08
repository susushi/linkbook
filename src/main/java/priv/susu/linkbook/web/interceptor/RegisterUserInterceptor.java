package priv.susu.linkbook.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import priv.susu.linkbook.user.User;
import priv.susu.linkbook.web.WebAttributes;
import priv.susu.linkbook.web.annotation.RegisterUserUrl;

@Service("registerUserInterceptor")
public class RegisterUserInterceptor implements HandlerInterceptor {

	private static final Logger INTERCEPTOR_LOG = LoggerFactory.getLogger("INTERCEPTOR_LOG");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(WebAttributes.REQUEST_CURRENT_USER);

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			RegisterUserUrl annotation = method.getMethodAnnotation(RegisterUserUrl.class);
			if (annotation != null) {
				if (user == null || user.getId() == -1) {
					redirectToLoginPage(request, response);
				}
				return true;
			}
		} else {
			INTERCEPTOR_LOG.error("Convert `HandlerMethod` failed. It will cause serious security problem.");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	/**
	 * 定向到错误提示页面
	 * 
	 * @param request
	 * @param response
	 * @param errorMessage
	 * @throws IOException
	 */
	private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login.htm");
	}

}
