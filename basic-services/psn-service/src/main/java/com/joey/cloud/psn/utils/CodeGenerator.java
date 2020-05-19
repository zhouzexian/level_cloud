package com.joey.cloud.psn.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 *
 * mybatis代码生成器
 * @author joey
 */
public class CodeGenerator {
    private static final String Prj="psn";
    private static final String MODLE="/Users/joey/work_space/git_space/level-cloud/basic-services/psn-service/src/main/java";

    private static final String URL="jdbc:mysql://127.0.0.1:3306/"+Prj+"_db?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
    private static final String NAME="root";
    private static final String PWD="123456";
    private static final String PARENT_PACKAGE="com.joey.cloud";

    public static void main(String[] args) {
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(MODLE);
        gc.setAuthor("Joey");
        gc.setOpen(false);
        gc.setSwagger2(true);
        //2、代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(gc);
        //3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(NAME);
        dsc.setPassword(PWD);
        mpg.setDataSource(dsc);
        //4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);
        //5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        //6、模版配置
        VelocityTemplateEngine v = new VelocityTemplateEngine();
        mpg.setTemplateEngine(v);
        mpg.execute();
    }


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


}