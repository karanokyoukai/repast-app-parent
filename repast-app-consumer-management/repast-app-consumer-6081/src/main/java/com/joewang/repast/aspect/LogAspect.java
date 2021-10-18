package com.joewang.repast.aspect;

import com.joewang.repast.annotation.LoginLogAnnotation;
import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.Member;
import com.joewang.repast.service.IRepastService;
import com.joewang.repast.utils.AddressUtil;
import com.joewang.repast.utils.DateUtils;
import com.joewang.repast.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.joewang.repast.staticstatus.StaticCode.*;

/**
 * @description:
 * @author: Joe Wang
 * @date: 2020-03-11
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private IRepastService repastService;

    /**
     * @desc:
     *      单纯的定义切面  指定让AOP在哪里生效
     *      也就是说当AOP检测到LoginLogAnnotation注解的时候，被该注解所标识的方法就会执行
     * @author: Joe Wang 
     * @date: 2020/3/11 
     * @param: []
     * @return: void 
     */
    @Pointcut("@annotation(com.joewang.repast.annotation.LoginLogAnnotation)")
    public void pointcut(){

    }

    /**
     * @desc:
     *      定义环形切面及所要执行的业务逻辑代码
     *      ProceedingJoinPoint 封装了目标路径 被自定注解所标识的方法中的所有参数
     *      因此可以通过这个参数获取目标路径的方法名、方法参数个数、方法参数类型、
     *          方法返回值、方法参数的值
     * @author: Joe Wang 
     * @date: 2020/3/11 
     * @param: [proceedingJoinPoint]
     * @return: java.lang.Object 
     */
    @Around("pointcut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取用户的ip地址
        String ipAddr = IPUtil.getIPAddr(request);

        // 获取MemberController中doLogin方法的参数对象
        Object[] args = proceedingJoinPoint.getArgs();
        // TODO 如何获取到Member
        Member member = new Member();
        for (Object object : args){
            member = (Member) object;
        }

        // 如何获取operationType和operationName值
        // 1.获取目标路径(只能指的是类-->MemberController)的全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 2.通过反射获取类对象
        Class targetClass = Class.forName(className);
        // 3.获取要切入的具体方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 4.MemberController中的所有方法
        Method[] methods = targetClass.getMethods();

        String operationType = "";
        String operationName = "";
        for (Method method : methods){
            // 5.判断方法名是否一致 因为可能出现方法重载，所以需要再次判断参数个数是否一致
            if (method.getName().equals(methodName)){
                Class[] parameterTypes = method.getParameterTypes();
                // 6.再次判断
                if (parameterTypes.length == args.length){
                    operationType = method.getAnnotation(LoginLogAnnotation.class).operationType();
                    operationName = method.getAnnotation(LoginLogAnnotation.class).operationName();
                    break;
                }
            }
        }

        /**
         * 第二种方式:
         *      通过用户的ip地址来获取用户的地理位置
         *      会使用到一个外部API(百度)
         *      ---->自己去定义一个工具类(向百度api去发送请求--->再去接收百度api所响应回来的数据)
         */
        // 百度api只能获取静态公网ip(俗称服务器的ip)--->或者获取运营商的手机ip
        // 只能模拟ip地址
        Map<String, Object> addressMap = AddressUtil.getAddresses(TEST_IP, ENCODING);

        String dataStr = DateUtils.format(new Date(), FORMAT_DATE);

        LoginLog loginLog = new LoginLog();
        loginLog.setLoginType(3);
        loginLog.setCreateTime(dataStr);
        loginLog.setIp(ipAddr);
        loginLog.setProvince((String) addressMap.get(PROVINCE));
        loginLog.setCity((String) addressMap.get(CITY));
        loginLog.setOperationType(operationType);
        loginLog.setOperationName(operationName);
        loginLog.setOpenId(member.getOpenId());
        // TODO  剩余参数的获取与set
        Boolean ifSuccess = repastService.addLoginLog(loginLog);
        if (ifSuccess){
            return result; // 执行结束，从切面返回controller
        }
        return null; // 直接结束，不再返回至controller中
    }
}
