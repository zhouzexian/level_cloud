package com.joey.cloud.mail.controller;

import com.joey.cloud.common.core.form.MailForm;
import com.joey.cloud.common.core.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author joey
 */
@Api(tags = "1、邮件 API")
@ApiSort(value = 1)
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Value("${spring.mail.username}")
    String addresser;
    @Resource
    JavaMailSender javaMailSender;

    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendOut")
    public ResponseVo sendOut(@RequestBody MailForm form){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            helper.setFrom(addresser);
            //收件人
            helper.setTo(form.getAddressee());
            //标题
            helper.setSubject(form.getSubject());
            //文本
            helper.setText(form.getText());
            if(StringUtils.isNotBlank(form.getFilePath())){
                //附件
                helper.addAttachment("downFile",new File(form.getFilePath()));
            }
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.error("发送邮件出错",e);
            return ResponseVo.error(e.getMessage());
        }
        return ResponseVo.success();
    }
}
