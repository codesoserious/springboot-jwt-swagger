package com.lead.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lead.annotations.PassToken;
import com.lead.annotations.UserLoginToken;
import com.lead.entity.User;
import com.lead.execption.CheckException;
import com.lead.service.UserService;
import com.lead.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author mac
 * @date 2020/9/13
 */
public class AuthenticationInterceptor implements HandlerInterceptor {


    private Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拿到请求头中的token
        String token = request.getHeader("token");
        logger.info("token的值为:{}", token);
        //判断是否映射到方法上,无则放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //强制装换拦截方法类,获取方法对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注解，没有则跳过验证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查是否有LoginToken注解，没有则跳过验证
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            logger.info("进入token验证");
            UserLoginToken loginToken = method.getAnnotation(UserLoginToken.class);
            if (loginToken.required()) {
                //执行认证
                if (StringUtils.isEmpty(token)) {
                    throw new CheckException("token为空，请重新登录");
                }
                String jwtId = "";
                try {
                    jwtId = JWT.decode(token).getId();
                } catch (JWTDecodeException e) {
                    throw new CheckException("此Token异常,请从新登陆");
                }
                //判断用户是否为空
                User user = userService.verifyUser(new User().setName(jwtId));
                if (ObjectUtils.isEmpty(user)) {
                    throw new CheckException(String.format("此%s用户不存在", jwtId));
                } else {
                    JwtUtils.verify(token, user.getPassword());
                }
            }
        }
        return true;
    }
}
