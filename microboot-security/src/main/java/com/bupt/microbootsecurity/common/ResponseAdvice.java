package com.bupt.microbootsecurity.common;

import com.bupt.microbootsecurity.common.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 如果引入了swagger或knife4j的文档生成组件，这里需要仅扫描自己项目的包，否则文档无法正常生成
@RestControllerAdvice(basePackages = "com.bupt.microbootsecurity.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果不需要进行封装的，可以添加一些校验手段，比如添加标记排除的注解，判断是否要交给beforeBodyWrite方法执行：
        // true: 需要 false: 不需要
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 对response进行具体的处理
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
        /**
         * 对于body是String类型时，selectedConverterType的参数值是 org.springframework.http.converter.StringHttpMessageConverter;
         * 对于body是其他数据类型，selectedConverterType的参数是 org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
         * 如果返回值是String类型，那就手动把Result对象转换成JSON字符串，处理cannot be cast to java.lang.String问题
         */

//        if (body instanceof String) {
//            try {
//                return this.objectMapper.writeValueAsString(Result.success(body));
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        }

        return Result.success(body);
    }
}
