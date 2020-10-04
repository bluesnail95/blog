package gdut.ff.generator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import gdut.ff.generator.jdbc.DataSourceConnect;
import gdut.ff.generator.util.PropertyUtil;
import gdut.ff.generator.util.StringUtil;

/**
 * @author liuffei
 * @date 2018-03-28
 * @description
 */
public class TestDynamicGenerator {
	
	public DataSourceConnect conn = new DataSourceConnect();

	/**
	 * 查询全部的数据库表
	 */
	public void testQueryAllTables() {
		List<String> list = conn.QueryAllTable();
		if(null != list && list.size() > 0) {
			for(int i = 0;i < list.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	@Test
	public void testConnection() {
		Connection connection = new DataSourceConnect ().getConnect();
		System.out.println(connection);
	}
	
	/**
	 * 查询全部的字段
	 */
	public void testQueryColumn() {
		List<Map<String,Object>> list = conn.queryColumn("user","blog");
		if(null != list && list.size() > 0) {
			for(int i = 0;i < list.size();i++) {
				Map<String,Object> column = list.get(i);
				for(Map.Entry<String, Object> entry:column.entrySet()) {
					System.out.print(entry.getKey()+"-->"+entry.getValue()+"  ");
				}
				System.out.println("");
			}
		}
	}
	
	/**
	 * 测试生成实体
	 */
	@Test
	public void testDomain() {
		String dataBaseName = conn.getDatabaseName();
		Configuration cfg = new Configuration();
		try {
			String path = PropertyUtil.PROJECT_PATH +  PropertyUtil.BASE_PATH;
			cfg.setDirectoryForTemplateLoading(new File(path+PropertyUtil.BASE_TEMPLATE_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			//Domain 
			Template template = cfg.getTemplate(PropertyUtil.DOMAIN_TEMPLATE_NAME);
			//查询出全部的表
			List<String> list = conn.QueryAllTable();
			if(null != list && list.size() > 0) {
				for(int i = 0;i < list.size();i++) {
					//类名
					String className = StringUtil.tableNameToClassName(list.get(i));
					Writer out = new OutputStreamWriter(new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/java"+"/"+StringUtil.formatPathName(PropertyUtil.DOMAIN_PACKAGE)+"/"+className+".java")),"UTF-8");
					List<Map<String, Object>> columns = conn.queryColumn(list.get(i),dataBaseName);
					
                    Map<String,Object> data = new HashMap<String,Object>();
                    data.put("columns", columns);
                    data.put("className", className);
                    data.put("tableName", list.get(i));
                    data.put("domainPackage", PropertyUtil.DOMAIN_PACKAGE);
                    template.process(data,out);
        			out.flush(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试生成Repository
	 */
	@Test
	public void testRepositoryGenerator() {
		String dataBaseName = conn.getDatabaseName();
		Configuration cfg = new Configuration();
		try {
			String path = PropertyUtil.PROJECT_PATH +  PropertyUtil.BASE_PATH;
			cfg.setDirectoryForTemplateLoading(new File(path+PropertyUtil.BASE_TEMPLATE_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(PropertyUtil.MAPPER_TEMPLATE_NAME);
			List<String> list = conn.QueryAllTable();
			if(null != list && list.size() > 0) {
				for(int i = 0;i < list.size();i++) {
					String className = StringUtil.tableNameToClassName(list.get(i));
					Writer out = new OutputStreamWriter(new FileOutputStream(new File(path+"/"+StringUtil.formatPathName(PropertyUtil.MAPPER_PACKAGE)+"/"+className+"Repository.java")),"UTF-8");
					List<Map<String, Object>> columns = conn.queryColumn(list.get(i),dataBaseName);
					
                    Map<String,Object> data = new HashMap<String,Object>();
                    data.put("columns", columns);
                    data.put("className", className);
                    data.put("repositoryPackage", PropertyUtil.MAPPER_PACKAGE);
                    data.put("domainPackage", PropertyUtil.DOMAIN_PACKAGE);
                    data.put("tableName", list.get(i));
					template.process(data,out);
					out.flush(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试生成Service
	 */
	@Test
	public void testServiceGenerator() {
		String dataBaseName = conn.getDatabaseName();
		Configuration cfg = new Configuration();
		try {
			String path = PropertyUtil.PROJECT_PATH +  PropertyUtil.BASE_PATH;
			cfg.setDirectoryForTemplateLoading(new File(path+PropertyUtil.BASE_TEMPLATE_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(PropertyUtil.SERVICE_TEMPLATE_NAME);
			List<String> list = conn.QueryAllTable();
			if(null != list && list.size() > 0) {
				for(int i = 0;i < list.size();i++) {
					String className = StringUtil.tableNameToClassName(list.get(i));
					Writer out = new OutputStreamWriter(new FileOutputStream(new File(path+"/"+StringUtil.formatPathName(PropertyUtil.SERVICE_PACKAGE)+"/"+className+"Service.java")),"UTF-8");
					List<Map<String, Object>> columns = conn.queryColumn(list.get(i),dataBaseName);
					
                    Map<String,Object> data = new HashMap<String,Object>();
                    data.put("columns", columns);
                    data.put("className", className);
                    data.put("beanName", className.substring(0,1).toLowerCase().concat(className).substring(1));
                    data.put("servicePackage", PropertyUtil.SERVICE_PACKAGE);
                    data.put("tableName", list.get(i));
                    data.put("repositoryPackage", PropertyUtil.MAPPER_PACKAGE);
                    data.put("domainPackage", PropertyUtil.DOMAIN_PACKAGE);
					template.process(data,out);
					out.flush(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试生成Controller
	 */
	@Test
	public void testControllerGenerator() {
		String dataBaseName = conn.getDatabaseName();
		Configuration cfg = new Configuration();
		try {
			String path = PropertyUtil.PROJECT_PATH +  PropertyUtil.BASE_PATH;
			cfg.setDirectoryForTemplateLoading(new File(path+PropertyUtil.BASE_TEMPLATE_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(PropertyUtil.CONTROLLER_TEMPLATE_NAME);
			List<String> list = conn.QueryAllTable();
			if(null != list && list.size() > 0) {
				for(int i = 0;i < list.size();i++) {
					String className = StringUtil.tableNameToClassName(list.get(i));
					Writer out = new OutputStreamWriter(new FileOutputStream(new File(path+"/"+StringUtil.formatPathName(PropertyUtil.CONTROLLER_PACKAGE)+"/"+className+"RestController.java")),"UTF-8");
					List<Map<String, Object>> columns = conn.queryColumn(list.get(i),dataBaseName);
					
                    Map<String,Object> data = new HashMap<String,Object>();
                    data.put("columns", columns);
                    data.put("className", className);
                    data.put("beanName", className.substring(0,1).toLowerCase().concat(className).substring(1));
                    data.put("servicePackage", PropertyUtil.SERVICE_PACKAGE);
                    data.put("tableName", list.get(i));
                    data.put("repositoryPackage", PropertyUtil.MAPPER_PACKAGE);
                    data.put("domainPackage", PropertyUtil.DOMAIN_PACKAGE);
                    data.put("controllerPackage", PropertyUtil.CONTROLLER_PACKAGE);
                    data.put("application", PropertyUtil.APPLICATION);
					template.process(data,out);
					out.flush(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPath() throws ClassNotFoundException {
		String classPath = this.getClass().getResource("/").getPath();
		System.out.println(System.getProperty("user.dir"));
	}
}
