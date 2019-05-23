package com.example.demospringboot.Exception;

import com.example.demospringboot.common.ResponseBody;
import com.example.demospringboot.utils.ResponseBodyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 异常处理类
 * 返回json格式
 *
 */
@ControllerAdvice
@RestController
@Slf4j
public class RestControllerException {

    /**
     * 描述：自定义异常捕获
     * @param
     * @return json
     */
    @ExceptionHandler(value=MyException.class)
    public ResponseBody responseException(MyException es) {
        log.info(es.getMessage(), es);
        return ResponseBodyUtil.exeption(es);
    }

    /**
     * 描述：@RequestBody @Validated 此两个注解验证参数才有效果
     * @param
     * @return json
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map<String, String> validExceptionHander(MethodArgumentNotValidException e){
        BindingResult bindingResult =e.getBindingResult();
        HashMap<String, String> resultException = new HashMap<>();
        resultException.put("code", "002");
        resultException.put("message",  bindingResult.getAllErrors().get(0).getDefaultMessage());
        return resultException;
    }

    /**
     * 描述：其他异常捕获
     * @param me
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> responseCommonException(Exception me) {
        log.error(me.getMessage(), me);
        HashMap<String, String> resultException = new HashMap<>();
        resultException.put("code", "500");
        resultException.put("message", me.getMessage());
        return resultException;
    }

}
