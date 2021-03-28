package com.mohey.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mohey El-Din Badr
 * MoheyElDin.Badr@gmail.com
 * on March 28, 2021
 */
@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.mohey.pma.controllers..*)" + " || within(com.mohey.pma.dao..*)")
    public void definePackagePointcuts(){
        // empty method just to name the locations specified in the pointcut
    }

    @Before("definePackagePointcuts()")
    public void log(){
        //@After logs the code after it hits or compiles the method, @Before is after the event trigger but before the method execution
        log.debug(" --------------------------------------------------------------------- ");
    }
}
