package gdut.ff.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author liuffei
 * @date 2018-03-28
 * @description
 */
public class PropertyUtil {

	public final static String APPLICATION;
	public final static String TABLE_NAME;
	public final static String COMMENT;
	
	public final static String JDBC_DRIVER;
	public  final static String JDBC_URL;
	public  final static String JDBC_USERNAME;
	public  final static String JDBC_PASSWORD;
	
	 /**
     * domain,dao,service,controller
     */
	public final static String DOMAIN_PACKAGE;
	public final static String MAPPER_PACKAGE;
	public final static String SERVICE_PACKAGE;
	public final static String CONTROLLER_PACKAGE;
	public final static String MYBATIS_PACKAGE;
	
	public final static String BASE_TEMPLATE_PATH;
	public final static String BASE_PATH;
	public final static String PROJECT_PATH;
	public final static String DOMAIN_TEMPLATE_NAME;
	public final static String MAPPER_TEMPLATE_NAME;
	public final static String SERVICE_TEMPLATE_NAME;
	public final static String CONTROLLER_TEMPLATE_NAME;
	public final static String MYBATIS_TEMPLATE_NAME;
	
	
	static {
		
		Properties properties = new Properties();
		InputStream inputStream = Properties.class.getResourceAsStream("/config.properties");
	    try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    APPLICATION = properties.getProperty("application");
	    TABLE_NAME = properties.getProperty("tableName");
	    COMMENT = properties.getProperty("comment");
	    
	    /**
	     * domain,dao,service,controller
	     */
	    DOMAIN_PACKAGE = properties.getProperty("domain.package");
	    MAPPER_PACKAGE = properties.getProperty("mapper.package");
	    SERVICE_PACKAGE = properties.getProperty("service.package");
	    CONTROLLER_PACKAGE = properties.getProperty("controller.package");
	    MYBATIS_PACKAGE = properties.getProperty("mybatis.package");
	    
	    JDBC_DRIVER = properties.getProperty("jdbc.driver");
	    JDBC_URL = properties.getProperty("jdbc.url");
	    JDBC_USERNAME = properties.getProperty("jdbc.username");
	    JDBC_PASSWORD = properties.getProperty("jdbc.password");
	
	    BASE_PATH = properties.getProperty("base.path");
	    PROJECT_PATH = System.getProperty("user.dir");
	    BASE_TEMPLATE_PATH = properties.getProperty("base.template.path");
	    DOMAIN_TEMPLATE_NAME = properties.getProperty("domain.template.name");
	    MAPPER_TEMPLATE_NAME = properties.getProperty("mapper.template.name");
	    SERVICE_TEMPLATE_NAME = properties.getProperty("service.template.name");
	    CONTROLLER_TEMPLATE_NAME = properties.getProperty("controller.template.name");
	    MYBATIS_TEMPLATE_NAME = properties.getProperty("mybatis.template.name");
	}
}
