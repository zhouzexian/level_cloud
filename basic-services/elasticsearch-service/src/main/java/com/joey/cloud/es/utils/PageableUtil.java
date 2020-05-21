package com.joey.cloud.es.utils;

import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.form.PageEsForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


/**
 * ES分页工具类
 * @author joey
 */
public class PageableUtil {

    public static Pageable getPageable(PageEsForm form){
        if(form==null){
            return PageRequest.of(BaseConstant.INIT_PAGE_NO_ES,BaseConstant.INIT_PAGE_SIZE_ES);
        }
        return PageRequest.of(form.getPageNo()==null||form.getPageNo()<1?BaseConstant.INIT_PAGE_NO_ES:form.getPageNo()-1,
                form.getPageSize()==null||form.getPageSize()<1?BaseConstant.INIT_PAGE_SIZE_ES:form.getPageSize());

    }
}
