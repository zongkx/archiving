package com.demo.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

 /**
  * <p>
  * 读取控制台内容
  * </p>
  */
 public static String scanner(String tip) {
     @SuppressWarnings("resource")
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

 public static void main(String[] args) {
     // 代码生成器
     AutoGenerator mpg = new AutoGenerator();

     // 全局配置
     GlobalConfig gc = new GlobalConfig();
     String projectPath = System.getProperty("user.dir");
     gc.setOutputDir(projectPath + "/src/main/java");
     gc.setAuthor("Raynor");
     gc.setOpen(false);
     mpg.setGlobalConfig(gc);

     // 数据源配置
     DataSourceConfig dsc = new DataSourceConfig();
     dsc.setUrl("jdbc:mysql://www.db4free.net:3306/raynor?useUnicode=true&useSSL=false&characterEncoding=utf8");
     // dsc.setSchemaName("public");
     dsc.setDriverName("com.mysql.cj.jdbc.Driver");
     dsc.setUsername("raynor");
     dsc.setPassword("mist1874");
     mpg.setDataSource(dsc);

     // 包配置
     PackageConfig pc = new PackageConfig();
     //pc.setModuleName(scanner("模块名"));
     pc.setParent("com.demo");
     mpg.setPackageInfo(pc);

     // 自定义配置
     InjectionConfig cfg = new InjectionConfig() {
         @Override
         public void initMap() {
             // to do nothing
         }
     };
     List<FileOutConfig> focList = new ArrayList<>();
     focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
         @Override
         public String outputFile(TableInfo tableInfo) {
             // 自定义输入文件名称
             return projectPath + "/src/main/resources/mapper/" 
                     + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
         }
     });
     cfg.setFileOutConfigList(focList);
     mpg.setCfg(cfg);
     mpg.setTemplate(new TemplateConfig().setXml(null));

     // 策略配置
     StrategyConfig strategy = new StrategyConfig();
     strategy.setNaming(NamingStrategy.underline_to_camel);
     strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
     strategy.setEntityLombokModel(true);
     strategy.setRestControllerStyle(true);
    // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
     strategy.setInclude(scanner("表名"));
     strategy.setSuperEntityColumns("id");
     strategy.setControllerMappingHyphenStyle(true);
     strategy.setTablePrefix(pc.getModuleName() + "_");
     mpg.setStrategy(strategy);
     mpg.setTemplateEngine(new FreemarkerTemplateEngine());
     mpg.execute();
 }

}
