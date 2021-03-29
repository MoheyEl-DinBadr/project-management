package com.mohey.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 28, 2021
 */
@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.mohey.pma.controllers..*)" /*+ " || within(com.mohey.pma.dao..*)"*/)
    public void definePackagePointcuts(){
        // empty method just to name the locations specified in the pointcut
    }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp){
        //@After logs the code after it hits or compiles the method, @Before is after the event trigger but before the method execution
        //@Around you can control before and after the method trigger
        log.debug("\n \n \n");
        log.debug("************ Before Method Execution ************ \n {}. {} () with arguments [s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));

        log.debug("______________________________________________________ \n \n \n");

        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.debug("\n \n \n");
        log.debug("************ After Method Execution ************ \n {}. {} () with arguments [s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));

        log.debug("______________________________________________________ \n \n \n");
        return o;
    }
}
