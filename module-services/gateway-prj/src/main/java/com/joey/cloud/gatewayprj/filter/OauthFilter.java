package com.joey.cloud.gatewayprj.filter;

import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.dto.AuthInfoDto;
import com.joey.cloud.common.core.dto.AuthorityUriDto;
import com.joey.cloud.common.core.utils.ResponseUtil;
import com.joey.cloud.common.core.vo.ResponseVo;
import com.joey.cloud.provider.uaa.feign.IUaaServiceFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 权限过滤控制器
 * @author joey
 */
@Component
public class OauthFilter extends ZuulFilter {
    public static String FILTER_TYPE_PRE="pre";
    @Resource
    IUaaServiceFeign uaaServiceFeign;
    /**
     * pre=路由之前；routing=路由之时；post=路由之后；error=发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return FILTER_TYPE_PRE;
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        //1、获取所有设置权限的uri
        final List<AuthorityUriDto> list = uaaServiceFeign.findUriList();
        if(list==null || list.size()==0){
            return true;
        }
        //2、正则匹配是否有访问权限uri
        List<AuthorityUriDto> filterList = list.parallelStream()
                .filter(r->
                        Pattern.matches(r.getUri(),request.getRequestURI())
                ).collect(Collectors.toList());
        if(filterList.size()==0){
            return true;
        }
        //3、是否存在token
        String token = request.getHeader(BaseConstant.HEADER_TOKEN);
        if(StringUtils.isBlank(token)){
            ResponseUtil.handle(response, ResponseVo.noAuthority(BaseConstant.ERROR_AUTH1));
            return false;
        }
        //4、校验token有效性
        ResponseVo<AuthInfoDto> responseVo = uaaServiceFeign.check(token);
        if(responseVo == null||responseVo.getStatus() != BaseConstant.SUCCESS_200){
            ResponseUtil.handle(response, ResponseVo.noAuthority(BaseConstant.ERROR_AUTH2));
            return false;
        }
        //5、校验token权限
        List<String> authorityKeyList = (List<String>)responseVo.getData().getAuthorityKeyList();
        if(checkRoles(authorityKeyList,filterList)){
            ResponseUtil.handle(response, ResponseVo.noAuthority(BaseConstant.ERROR_AUTH3));
            return false;
        }
        return true;
    }

    /**
     * 校验是否存在权限
     * @param authorityKeyList 用户权限列表
     * @param filterList uri权限列表
     * @return
     */
    private boolean checkRoles(List<String> authorityKeyList, List<AuthorityUriDto> filterList) {
        for(AuthorityUriDto one:filterList){
            for(String authorityKey:authorityKeyList){
                if(one.getAuthorityKey().equals(authorityKey)){
                    return false;
                }
            }
        }
        return true;
    }
}
