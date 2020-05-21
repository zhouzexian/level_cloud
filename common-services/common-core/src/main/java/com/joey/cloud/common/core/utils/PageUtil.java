package com.joey.cloud.common.core.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joey.cloud.common.core.constant.BaseConstant;
import com.joey.cloud.common.core.form.PageForm;

import java.awt.print.Pageable;

/**
 * @author joey
 */
public class PageUtil {

    public static Page getPage(PageForm form){
        if(form==null){
            return new Page<>(BaseConstant.INIT_PAGE_NO,BaseConstant.INIT_PAGE_SIZE);
        }
        return new Page<>(form.getPageNo()==null||form.getPageNo()<1?BaseConstant.INIT_PAGE_NO:form.getPageNo(),
                form.getPageSize()==null||form.getPageSize()<1?BaseConstant.INIT_PAGE_SIZE:form.getPageSize());
    }

    public static Page getMaxPage(){
        return new Page<>(BaseConstant.INIT_PAGE_NO,BaseConstant.MAX_PAGE_SIZE);
    }
}
