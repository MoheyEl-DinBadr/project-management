package com.mohey.pma.controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorTestAppController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());

            switch (statusCode){
                case 500:
                    return "errorpages/error-500";
                case 404:
                    return "errorpages/error-404";
                case 403:
                    return "errorpages/error-403";
            }
        }
        return "errorpages/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
