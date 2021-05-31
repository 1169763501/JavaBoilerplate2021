package com.nbnfsoft.admin.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath===================================="+projectPath);
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setOutputDir("d:/demo/src/main/java");
        //gc.setAuthor("jobob");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setDateType(DateType.ONLY_DATE);
        gc.setMapperName("%sRepository"); // 设置mapper接口后缀
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sManager");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:oracle:thin:@121.40.57.176:1521:orcl");
        // dsc.setSchemaName("public");
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("KLB_appointment");
        dsc.setPassword("klb2017!");
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("char")) {
                    return DbColumnType.STRING;
                } else {
                    if (!t.contains("date") && !t.contains("timestamp")) {
                        if (t.contains("number")) {
                            if (t.matches("number\\(+\\d\\)")) {
                                return DbColumnType.INTEGER;
                            }
                            return DbColumnType.LONG;
                        }

                        if (t.contains("float")) {
                            return DbColumnType.FLOAT;
                        }

                        if (t.contains("clob")) {
                            return DbColumnType.STRING;
                        }

                        if (t.contains("blob")) {
                            return DbColumnType.BLOB;
                        }

                        if (t.contains("binary")) {
                            return DbColumnType.BYTE_ARRAY;
                        }

                        if (t.contains("raw")) {
                            return DbColumnType.BYTE_ARRAY;
                        }
                    } else {
                        switch (globalConfig.getDateType()) {
                            case ONLY_DATE:
                                return DbColumnType.DATE;
                            case SQL_PACK:
                                return DbColumnType.TIMESTAMP;
                            case TIME_PACK:
                                return DbColumnType.LOCAL_DATE_TIME;
                        }
                    }
                    return DbColumnType.STRING;
                }
            }
        });
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.nbnfsoft.admin");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/repository/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
//         templateConfig.setService();
//         templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("net.xdclass.demo2.model.BaseEntity");
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("net.xdclass.demo2.model.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        String table = "NFSUR_MENU";
        strategy.setInclude(table.split(","));
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        try {
            customPackagePath(pc,mpg);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mpg.execute();
    }

    public static void customPackagePath(PackageConfig pc,AutoGenerator mpg) throws NoSuchFieldException, IllegalAccessException {
        String projectPath = System.getProperty("user.dir");
        String mavenPath = "\\src\\main\\java\\";
        String srcPath = projectPath+mavenPath;
//        srcPath = "d:/demo1/src/main/java/";
        String moduleName = pc.getModuleName();

        /**
         * 这里包路径可以根据实际情况灵活配置
         */
        Map<String,String> packageInfo = new HashMap<>();
        packageInfo.put(ConstVal.CONTROLLER, "com.nbnfsoft.admin.controller");
        packageInfo.put(ConstVal.SERVICE, "com.nbnfsoft.admin.service");
        packageInfo.put(ConstVal.SERVICE_IMPL, "com.nbnfsoft.admin.manager");
        packageInfo.put(ConstVal.ENTITY, "com.nbnfsoft.admin.entity");
        packageInfo.put(ConstVal.MAPPER, "com.nbnfsoft.admin.repository");

        /**
         * srcPath也可以更具实际情况灵活配置
         * 后面部分的路径是和上面packageInfo包路径对应的源码文件夹路径
         * 这里你可以选择注释其中某些路径，可忽略生成该类型的文件，例如:注释掉下面pathInfo中Controller的路径，就不会生成Controller文件
         */
        Map pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.CONTROLLER_PATH, srcPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_PATH, srcPath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, srcPath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.ENTITY_PATH, srcPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.MAPPER_PATH, srcPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.XML_PATH, projectPath+"\\src\\main\\resources\\repository\\");
        pc.setPathInfo(pathInfo);

        /**
         * 创建configBuilder对象，传入必要的参数
         * 将以上的定义的包路径packageInfo配置到赋值到configBuilder对象的packageInfo属性上
         * 因为packageInfo是私有成员变量，也没有提交提供公共的方法，所以使用反射注入
         * 为啥要这么干，看源码去吧
         */
        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
        Field packageInfoField = configBuilder.getClass().getDeclaredField("packageInfo");
        packageInfoField.setAccessible(true);
        packageInfoField.set(configBuilder,packageInfo);

        /**
         * 设置配置对象
         */
        mpg.setConfig(configBuilder);
    }

}