package priv.susu.linkbook.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 当控制器的方法存在该annotation时，则认为该URL只有登录用户才能访问的
 *
 * @author yichen
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterUserUrl {

	/**
	 * 是否为ajax请求
	 * 
	 * @return ajax
	 */
	boolean ajax() default false;

	/**
	 * 是否需要验证用户才能访问
	 * 
	 * @return
	 */
	boolean isVerified() default true;
}
