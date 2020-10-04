package gdut.ff.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import gdut.ff.generator.jdbc.DataSourceConnect;
import gdut.ff.generator.util.PropertyUtil;
import gdut.ff.generator.util.StringUtil;

/**
 * 生成指定表的domain,po,dao,service,controller文件
 * @author liuffei
 * @date 2018年3月29日
 * @description
 */
public class TableGenerator {

	public DataSourceConnect conn = new DataSourceConnect();
	
	public void generateFile(String templateName, String packageName, String tableName, String typeName) {
		String dataBaseName = conn.getDatabaseName();
		Configuration cfg = new Configuration();
		try {
			String path = PropertyUtil.PROJECT_PATH +  PropertyUtil.BASE_PATH;
			cfg.setDirectoryForTemplateLoading(new File(path+PropertyUtil.BASE_TEMPLATE_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(templateName);
			//List<String> list = conn.QueryAllTable();
			String className = StringUtil.tableNameToClassName(tableName);
			Writer out = new OutputStreamWriter(new FileOutputStream(new File(PropertyUtil.PROJECT_PATH + "/src/main/resources/" + StringUtil.formatPathName(packageName) + "/" + className + typeName + ".xml")), "UTF-8");
			List<Map<String, Object>> columns = conn.queryColumn(tableName,dataBaseName);
			
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("columns", columns);
            data.put("className", className);
            data.put("beanName", className.substring(0,1).toLowerCase().concat(className.substring(1)));
            data.put("application", PropertyUtil.APPLICATION);
            data.put("comment", PropertyUtil.COMMENT);
			template.process(data,out);
			out.flush(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		TableGenerator generator = new TableGenerator();
		//generator.generateFile(PropertyUtil.DOMAIN_TEMPLATE_NAME, PropertyUtil.DOMAIN_PACKAGE, PropertyUtil.TABLE_NAME, "");
		//generator.generateFile(PropertyUtil.MAPPER_TEMPLATE_NAME, PropertyUtil.MAPPER_PACKAGE, PropertyUtil.TABLE_NAME, "Mapper");
		//generator.generateFile(PropertyUtil.SERVICE_TEMPLATE_NAME, PropertyUtil.SERVICE_PACKAGE, PropertyUtil.TABLE_NAME, "ServiceImpl");
		//generator.generateFile(PropertyUtil.CONTROLLER_TEMPLATE_NAME, PropertyUtil.CONTROLLER_PACKAGE, PropertyUtil.TABLE_NAME, "Controller");
		generator.generateFile(PropertyUtil.MYBATIS_TEMPLATE_NAME, PropertyUtil.MYBATIS_PACKAGE, PropertyUtil.TABLE_NAME, "");
	}
	
}
