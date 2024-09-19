package com.email.spring_app.utils;

import org.springframework.web.servlet.ModelAndView;

public abstract class TemplateUtil {
    
    public static ModelAndView generate(final String template) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(template);
        return modelAndView;
    }
}
