package gdut.ff.mydispatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import gdut.ff.mydispatcher.annotation.MyAutowired;
import gdut.ff.mydispatcher.annotation.MyController;
import gdut.ff.mydispatcher.annotation.MyRequestMapping;
import gdut.ff.mydispatcher.annotation.MyService;


public class MyDispatcherServlet extends HttpServlet{
	
	//aplication2.properties的内容都存入properties中
	private Properties properties = new Properties();
	
	//存储文件名称
	private List<String> classNames = new ArrayList<String>();
	
	//IOC容器
	private Map<String, Object> ioc = new HashMap<String, Object>();
	
	//处理器
	private Map<String, Method> handlerMapping = new HashMap<String, Method>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		url = url.replace(req.getContextPath(), "");
		if(handlerMapping.containsKey(url)) {
			resp.getWriter().write("404 not found");
		}
		Method method = handlerMapping.get(url);
		System.out.println(method);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		//1.加载application.xml文件
		doInitPropertiesConfig(config.getInitParameter("contextConfigLocation"));
		
		//2.扫描包，初始化类
		doScanner(properties.getProperty("scanPackage"));
		
		//3.初始化IOC容器。
		doInstance();
		
		//4.依赖注入
		doAutowired();
		
		//5.初始化HandlerMapping
		doHandlerMapping();
		
		System.out.println("My Dispatcher Servlet初始化成功");
		super.init(config);
	}
	
	//初始化HandlerMapping
	public void doHandlerMapping() {
		if(ioc.isEmpty()) { return;}
		for(Map.Entry<String, Object> entry: ioc.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
			if(!clazz.isAnnotationPresent(MyController.class)) { continue;}
			String baseUrl = "";
			if(clazz.isAnnotationPresent(MyRequestMapping.class)) {
				MyRequestMapping mapping = clazz.getAnnotation(MyRequestMapping.class);
				baseUrl = mapping.value();
			}
			Method methods[] = clazz.getMethods();
			for(Method method: methods) {
				if(method.isAnnotationPresent(MyRequestMapping.class)) {
					String url = baseUrl + method.getAnnotation(MyRequestMapping.class).value();
					handlerMapping.put(url, method);
				}
			}
		}
	}

	//依赖注入
	public void doAutowired() {
		if(ioc.isEmpty()) { return;}
		for(Map.Entry<String, Object> entry: ioc.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for(Field field: fields) {
				if(!field.isAnnotationPresent(MyAutowired.class)) { continue;}
				MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
				String beanName = myAutowired.value();
				if("".equals(beanName)) {
					beanName = field.getType().getName();
				}
				field.setAccessible(true);
				try {
					field.set(entry.getValue(), ioc.get(beanName));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				} 
			}
		}
	}
	
	//首字母转小写
	public String lowerFirstCase(String name) {
		char[] charArray = name.toCharArray();
		charArray[0] +=32;
		return String.valueOf(charArray);
	}
	
	//初始化IOC容器
	public void doInstance() {
		if(classNames.isEmpty()) return;
		try {
			for(String className: classNames) {
				Class<?> clazz = Class.forName(className);
				
				if(clazz.isAnnotationPresent(MyController.class)) {
					//带有MyController注解
					//1.先查看注解的value是否有值，
					MyController myController = clazz.getAnnotation(MyController.class);
					String beanName = myController.value();
					if("".equals(beanName.trim())) {
						//2.如果注解的value没有值，就将类名的首字母转小写
						beanName = lowerFirstCase(clazz.getSimpleName());
					}
					//实例化
					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					//3.如果存在接口，就将接口的全类名作为key
					Class<?> interfaces[] = clazz.getInterfaces();
					for(Class interfaceClass: interfaces) {
						ioc.put(interfaceClass.getName(), instance);
					}
				}else if(clazz.isAnnotationPresent(MyService.class)) {
					//是MyService注解
					//1.先查看注解的value是否有值，
					MyService myService = clazz.getAnnotation(MyService.class);
					String beanName = myService.value();
					if("".equals(beanName.trim())) {
						//2.如果注解的value没有值，就将类名的首字母转小写
						beanName = lowerFirstCase(clazz.getSimpleName());
					}
					//实例化
					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					//3.如果存在接口，就将接口的全类名作为key
					Class<?> interfaces[] = clazz.getInterfaces();
					for(Class interfaceClass: interfaces) {
						ioc.put(interfaceClass.getName(), instance);
					}
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//初始化classNames的内容
	private void doScanner(String scanPackageName) {
		try {
			String path = ResourceUtils.getURL("classpath:").getPath() + "/" + scanPackageName.replaceAll("\\.", "/") + "/";
			File root = new File(path);
			for(File child: root.listFiles()) {
				if(child.isDirectory()) {
					doScanner(scanPackageName + "." + child.getName());
				}else {
					classNames.add(scanPackageName + "." + child.getName().replace(".class", ""));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//初始化properties的内容
	public void doInitPropertiesConfig(String propertiesLocation) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesLocation);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
