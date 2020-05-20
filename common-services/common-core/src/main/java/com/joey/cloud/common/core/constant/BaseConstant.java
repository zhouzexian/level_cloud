package com.joey.cloud.common.core.constant;

/**
 * 基础常量
 * @author joey
 */
public class BaseConstant {
    public static final String SPITS_STR1="_";
    public static final String SPITS_STR2="/";
    public static final String SPITS_STR3=",";
    public static final String SPITS_STR4=".";

    public static final String STR_EMPTY="";

    public static final long MAX_PAGE_SIZE=999;
    public static final long INIT_PAGE_NO=1;
    public static final long INIT_PAGE_SIZE=10;

    public static final String HEADER_TOKEN="Authorization";
    public static final String TOKEN_BEARER="Bearer";

    public static final int SUCCESS_200=200;
    public static final String SUCCESS_STR="请求成功";

    public static final int ERROR_500=500;
    public static final String ERROR_STR="请求失败";

    public static final int ERROR_403=403;
    public static final String ERROR_AUTH1="token不存在";
    public static final String ERROR_AUTH2="token已过期";
    public static final String ERROR_AUTH3="无权限访问该操作";
    public static final String ERROR_AUTH4="用户名或密码不能为空";
    public static final String ERROR_AUTH5="用户名或密码错误";

}
