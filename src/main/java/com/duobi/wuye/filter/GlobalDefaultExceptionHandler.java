package com.duobi.wuye.filter;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

//本意是要处理以下问题：当上传的文件大小超出2M限制的时候，controller中会抛出异常，但是这个异常在controller中捕获不到，但是在这里可以捕获。但是这里捕获以后返回给前端时候出问题，无法跨域，前端无法收到返回的内容。
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = MultipartException.class)
    public String fileErrorHandler(MultipartException ex) {
//        ResponseJson responseJson = new ResponseJson();
//        responseJson.setSuccess(false);
//        responseJson.setMsg("false");
//        return responseJson;
        return "redirect:/uploadsize";
//        return "forward:/uploadsize";
    }
}
