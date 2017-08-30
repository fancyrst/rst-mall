package com.rstang.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * ������֧����
 * Created by yeyx on 2017/8/30.
 */
public abstract class BaseController {
    /**
     * ��־����
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * �������·��
     */
    @Value("${adminPath}")
    protected String adminPath;

    /**
     * ǰ�˻���·��
     */
    @Value("${frontPath}")
    protected String frontPath;

    /**
     * ǰ��URL��׺
     */
    @Value("${urlSuffix}")
    protected String urlSuffix;

    /**
     * ��֤Beanʵ������
     */
    @Autowired
    protected Validator validator;



    /**
     * ���Model��Ϣ
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * ���Flash��Ϣ
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * �ͻ��˷����ַ���
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * �������쳣
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {
        return "error/400";
    }


    /**
     * ��ʼ�����ݰ�
     * 1. �����д��ݽ�����String����HTML���룬��ֹXSS����
     * 2. ���ֶ���Date����ת��ΪString����
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    }

}
