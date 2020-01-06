package com.aisile04.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerDemo {

	public static void main(String[] args) {
		/**
		 * 第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
			第二步：设置模板文件所在的路径。
			第三步：设置模板文件使用的字符集。一般就是 utf-8.
			第四步：加载一个模板，创建一个模板对象。
			第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
			第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
			第七步：调用模板对象的 process 方法输出文件。
			第八步：关闭流
		 */
		//1：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		try {
			//2设置模板文件所在的路径。
			configuration.setDirectoryForTemplateLoading(new File("E:\\云计算专业\\专业前六个月\\云.java基础  专业一\\Eclipse安装\\eclipse\\r\\aisile04-parent\\aisile04-content-service\\src\\main\\webapp\\"));
			//3.设置模板文件使用的字符集。一般就是 utf-8
			configuration.setDefaultEncoding("utf-8");
			//4.加载一个模板，创建一个模板对象
			Template template = configuration.getTemplate("hello.ftl");
			//5.创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map
			Map map=new HashMap();
			map.put("name", "张三 ");
			map.put("message", "欢迎来到神奇的爱思乐世界！");
			
			map.put("success", true);
			
			List goodsList=new ArrayList();
			Map goods1=new HashMap();
			goods1.put("name", "苹果");
			goods1.put("price", 5.8);
			Map goods2=new HashMap();
			goods2.put("name", "香蕉");
			goods2.put("price", 2.5);
			Map goods3=new HashMap();
			goods3.put("name", "橘子");
			goods3.put("price", 3.2);
			goodsList.add(goods1);
			goodsList.add(goods2);
			goodsList.add(goods3);
			map.put("goodsList", goodsList);
			//存储时间
			map.put("today", new Date());
			//存储多位数字
			map.put("point", 102920122);
			//6.创建Writer对象
			Writer writer = new FileWriter(new File("d:\\temp\\hello.html"));
			//输出
			template.process(map, writer);
			//关闭Writer对象
			writer.close();
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
