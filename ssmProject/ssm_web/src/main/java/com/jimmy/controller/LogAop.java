package com.jimmy.controller;

import com.jimmy.domain.SysLog;
import com.jimmy.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Before("execution(* com.jimmy.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
          visitTime=new Date();
          clazz=joinPoint.getTarget().getClass();
          String methodName=joinPoint.getSignature().getName();
          Object[] args = joinPoint.getArgs();
          if (args==null||args.length==0){
              method=clazz.getMethod(methodName);
          }else {
              Class[] classArgs=new Class[args.length];
              for (int i =0;i<args.length;i++){
                  classArgs[i]=args[i].getClass();
              }
              method=clazz.getMethod(methodName,classArgs);
          }


    }

    @After("execution(* com.jimmy.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        long executionTime =new Date().getTime()-visitTime.getTime();


        String url = "";
        if(clazz!=null&&method!=null&&clazz!= LogAop.class){
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                }
            }
        }

        String ip = request.getRemoteAddr();

        SecurityContext context= SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        SysLog sysLog =new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类]"+clazz.getName()+"-[方法]"+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        sysLogService.save(sysLog);
    }
}
