package com.example.demospringboot.Interceptor;

import com.example.demospringboot.Exception.MyException;
import com.example.demospringboot.enums.ExceptionStatusEnum;
import com.example.demospringboot.utils.RSAEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class SignatureInterceptor implements HandlerInterceptor {
    private final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDkQeIiZAdGGeeL6YNQHuXFO9BPuKZREu7MGOen3jPrIIOeh4U88bFx89OqQs3pRC/6cvebu0s27HvHRi1QRxp8gkgFnqcE4TlZwWt+VwpLv3yHyoBKrHsRc/+QPiwJlwJiHujrYMp8Fli7t7nlClAm0+Qqmxo43YsTWXYjE8Tj2wIDAQAB";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sign = request.getHeader("sign");
        String data = request.getParameter("data");
        if(StringUtils.isEmpty(sign))
            throw new MyException("003","签名串为空");
        if(StringUtils.isEmpty(data))
            throw new MyException("003","原始数据为空");
        try {
            if (!RSAEncryptUtil.verify(publicKey, sign, data)) {
                log.info("验签失败"+"原始数据："+data+",签名串"+sign);
                throw new MyException(ExceptionStatusEnum.SIGNATURE_EXCEPTION, new RuntimeException());
            }
        } catch (Exception e) {
            log.info("验签失败");
            throw new MyException(ExceptionStatusEnum.SIGNATURE_EXCEPTION, e);
        }
        log.info("验签成功");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
