package com.joey.cloud.common.core.utils;

import com.alibaba.fastjson.JSON;
import com.joey.cloud.common.core.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应返回封装
 * @author joey
 */
public class ResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
    public static  void  handle(HttpServletResponse response, ResponseVo dto){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(dto));
        }catch (Exception e){
            logger.error("响应返回出错",e);
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }
}
