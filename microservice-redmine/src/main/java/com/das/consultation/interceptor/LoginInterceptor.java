package com.das.consultation.interceptor;

import com.taskadapter.redmineapi.RedmineManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wjian17 on 2017/9/25.
 */
@Aspect
@Configuration
public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Pointcut("execution(public * com.das.consultation.controller.*Controller.*Controller(..))")
	public void controllerMethodLogger() {}

	@Around("controllerMethodLogger()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sRequestAttr = (ServletRequestAttributes) requestAttr;
		HttpServletRequest request = sRequestAttr.getRequest();
		HttpServletResponse response = sRequestAttr.getResponse();

		String addr = request.getRemoteAddr(); //客户端ip
		String url = request.getRequestURL().toString(); //请求地址
		String method = request.getMethod(); //请求方式
		String queryString = request.getQueryString(); //查询参数
		logger.info(
				"controller请求开始, addr：{}, url: {}, method: {}, params: {}",
				addr, url, method, queryString);

		//Object[] args = pjp.getArgs();
		//LOGGER.info("请求参数, {}", JSON.toJSONString(args[0]));

		HttpSession session = request.getSession();
		RedmineManager redmineManager = (RedmineManager) session.getAttribute("redmine");
		if (redmineManager == null) {
			response.setHeader("sessionStatus", "null");
			return null;
		}

		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();
		return result;
	}
}
